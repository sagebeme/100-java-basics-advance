// Everything the site knows about the course, read from the repo at build time. Server-only: it
// uses the file system, so it runs while Next builds the pages, never in a visitor's browser.
import fs from "node:fs";
import path from "node:path";

export const REPO = "https://github.com/sagebeme/100-java-basics-advance";
export const BRANCH = "master";
const ROOT = path.join(process.cwd(), ".."); // the course repo; the site lives in site/

// The five stages, as the main README describes them.
export const STAGES = [
  { n: 1, name: "Beginner", first: 1, last: 14, learn: "variables, types, control flow, loops, methods, collections", ends: "Blackjack and Higher Lower" },
  { n: 2, name: "Intermediate", first: 15, last: 31, learn: "object-oriented programming, JavaFX, files, CSV, streams, JSON", ends: "a flash card app" },
  { n: 3, name: "Intermediate+", first: 32, last: 58, learn: "HTTP APIs, email, scraping, Selenium automation, then Spring Boot", ends: "a flight deal finder and your first Spring app" },
  { n: 4, name: "Advanced", first: 59, last: 80, learn: "forms, JPA databases, REST APIs, Spring Security, Docker, data analysis, algorithms, testing", ends: "a house price predictor" },
  { n: 5, name: "Professional", first: 81, last: 100, learn: "20 portfolio projects: games, web apps, automation, analytics", ends: "an earnings prediction system" },
] as const;

export type Stage = (typeof STAGES)[number];

export interface Day {
  day: number;
  stage: number;
  title: string;
  folder: string; // "day07"
  capstone: boolean;
  objectives: string[];
  checklist: string[]; // the day's own checklist, or a standard one
  tests: number; // JUnit test methods in the day's folder
  testDirs: string[]; // where to run them: a Maven project, or a src/ folder of plain .java files
  maven: boolean; // from Day 54, each day is a Maven project
  javafx: boolean;
}

const folderOf = (n: number) => (n < 100 ? `day${String(n).padStart(2, "0")}` : "day100");

// Plain text from a line of Markdown: no backticks, bold or links.
const plain = (md: string) =>
  md
    .replace(/`([^`]*)`/g, "$1")
    .replace(/\*\*([^*]*)\*\*/g, "$1")
    .replace(/\[([^\]]*)\]\([^)]*\)/g, "$1")
    .trim();

// The bullet items of one "## …" section, found by a word in its heading.
function bullets(md: string, heading: RegExp, pattern = /^- (?:\[ \] )?(.+)$/): string[] {
  const lines = md.split(/\r?\n/);
  const start = lines.findIndex((l) => l.startsWith("## ") && heading.test(l));
  if (start === -1) return [];
  const out: string[] = [];
  for (const line of lines.slice(start + 1)) {
    if (line.startsWith("## ")) break;
    const m = pattern.exec(line.trim());
    if (m) out.push(plain(m[1]));
  }
  return out;
}

function countTests(dir: string): number {
  let count = 0;
  const walk = (d: string) => {
    for (const entry of fs.readdirSync(d, { withFileTypes: true })) {
      if (entry.name === "target" || entry.name === "node_modules") continue;
      const full = path.join(d, entry.name);
      if (entry.isDirectory()) walk(full);
      else if (entry.name.endsWith("Test.java")) count += (fs.readFileSync(full, "utf8").match(/@(Test|ParameterizedTest)\b/g) ?? []).length;
    }
  };
  walk(dir);
  return count;
}

// The folders a learner runs tests from: the nearest folder with a pom.xml, or else the folder the
// test file sits in (plain javac + the JUnit console jar). The main project (_end) comes first.
function testDirs(dir: string): string[] {
  const found = new Set<string>();
  const walk = (d: string) => {
    for (const entry of fs.readdirSync(d, { withFileTypes: true })) {
      if (entry.name === "target") continue;
      const full = path.join(d, entry.name);
      if (entry.isDirectory()) walk(full);
      else if (entry.name.endsWith("Test.java")) {
        let at = path.dirname(full);
        let project: string | null = null;
        for (let up = at; up.startsWith(dir); up = path.dirname(up)) {
          if (fs.existsSync(path.join(up, "pom.xml"))) { project = up; break; }
        }
        found.add(path.relative(ROOT, project ?? at).split(path.sep).join("/"));
      }
    }
  };
  walk(dir);
  const rank = (p: string) => (p.includes("/_end") ? 0 : p.includes("exercise1") ? 1 : 2);
  return [...found].sort((a, b) => rank(a) - rank(b) || a.localeCompare(b));
}

function hasFile(dir: string, name: string): boolean {
  return fs.readdirSync(dir, { withFileTypes: true }).some((e) =>
    e.isDirectory() ? e.name !== "target" && hasFile(path.join(dir, e.name), name) : e.name === name,
  );
}

export function readme(n: number): string {
  return fs.readFileSync(path.join(ROOT, folderOf(n), "README.md"), "utf8");
}

let cache: Day[] | null = null;

export function allDays(): Day[] {
  if (cache) return cache;
  cache = Array.from({ length: 100 }, (_, i) => {
    const n = i + 1;
    const folder = folderOf(n);
    const md = readme(n);
    const heading = /^# Day (\d+) - (.+)$/m.exec(md);
    if (!heading || Number(heading[1]) !== n) throw new Error(`${folder}/README.md should start with "# Day ${n} - Title"`);
    const raw = heading[2].trim();
    const title = raw.replace(/^(Portfolio Project|Capstone Project|Capstone|Portfolio):\s*/i, "").replace(/\s*Capstone Project$/i, "");
    const checklist = bullets(md, /Checklist/, /^- \[ \] (.+)$/);
    const dir = path.join(ROOT, folder);
    const stage = STAGES.find((s) => n >= s.first && n <= s.last)!.n;
    return {
      day: n,
      stage,
      title,
      folder,
      capstone: /capstone/i.test(raw),
      objectives: bullets(md, /Learning Objectives/),
      checklist: checklist.length ? checklist : ["Read the lesson", "Built the project", "Tests pass", "Committed code to Git"],
      tests: countTests(dir),
      testDirs: testDirs(dir),
      maven: hasFile(dir, "pom.xml"),
      javafx: /javafx/i.test(md.slice(0, 1500)) || /javafx/i.test(raw),
    };
  });
  return cache;
}

export function getDay(n: number): Day | undefined {
  return allDays()[n - 1];
}

export function totals() {
  const days = allDays();
  return {
    tests: days.reduce((sum, d) => sum + d.tests, 0),
    daysWithTests: days.filter((d) => d.tests > 0).length,
    capstones: days.filter((d) => d.capstone).length,
  };
}

// What the browser-side scripts need: small, and serialisable.
export type DaySummary = Pick<Day, "day" | "stage" | "title" | "capstone" | "tests" | "maven"> & { brief: string; items: number };
export function summaries(): DaySummary[] {
  return allDays().map(({ day, stage, title, capstone, tests, maven, objectives, checklist }) => ({
    day,
    stage,
    title,
    capstone,
    tests,
    maven,
    brief: objectives.slice(0, 3).join(" · "),
    items: checklist.length,
  }));
}

export function githubUrl(repoPath: string, isFile: boolean): string {
  return `${REPO}/${isFile ? "blob" : "tree"}/${BRANCH}/${repoPath}`;
}
