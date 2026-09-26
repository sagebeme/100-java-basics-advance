"use client";
// Progress, kept in this browser only: { "7": [true, false, …] } is Day 7's checklist.
import { useSyncExternalStore } from "react";

const KEY = "java100-progress";
export type Progress = Record<string, boolean[]>;

const listeners = new Set<() => void>();
function read(): string {
  try {
    return localStorage.getItem(KEY) ?? "{}";
  } catch {
    return "{}"; // storage blocked (private window): nothing is saved
  }
}
function subscribe(listener: () => void) {
  listeners.add(listener);
  const onStorage = (e: StorageEvent) => e.key === KEY && listener(); // another tab ticked something
  window.addEventListener("storage", onStorage);
  return () => {
    listeners.delete(listener);
    window.removeEventListener("storage", onStorage);
  };
}

export function parse(raw: string): Progress {
  try {
    const value = JSON.parse(raw);
    return value && typeof value === "object" && !Array.isArray(value) ? value : {};
  } catch {
    return {};
  }
}

// The saved progress. On the server, and in the first render in the browser, it's empty, so the
// HTML matches; the real ticks appear straight after.
export function useProgress(): Progress {
  const raw = useSyncExternalStore(subscribe, read, () => "{}");
  return parse(raw);
}

export function setTicks(day: number, ticks: boolean[]) {
  const progress = parse(read());
  progress[String(day)] = ticks;
  try {
    localStorage.setItem(KEY, JSON.stringify(progress));
  } catch {
    /* not saved, but the page still works */
  }
  listeners.forEach((l) => l());
}

export function percent(progress: Progress, day: number, items: number): number {
  const ticks = progress[String(day)] ?? [];
  const done = ticks.slice(0, items).filter(Boolean).length;
  return items ? Math.round((done / items) * 100) : 0;
}

export const isDone = (p: Progress, day: number, items: number) => percent(p, day, items) === 100;

// Where to go next: the first unfinished day from the last one touched. Null if nothing is ticked.
export function nextDay(p: Progress, items: number[]): number | null {
  const touched = items.map((_, i) => i + 1).filter((d) => (p[String(d)] ?? []).some(Boolean));
  if (!touched.length) return null;
  const last = touched[touched.length - 1];
  if (!isDone(p, last, items[last - 1])) return last;
  for (let d = last + 1; d <= items.length; d++) if (!isDone(p, d, items[d - 1])) return d;
  for (let d = 1; d <= items.length; d++) if (!isDone(p, d, items[d - 1])) return d;
  return items.length;
}
