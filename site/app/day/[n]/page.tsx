import type { Metadata } from "next";
import Link from "next/link";
import { notFound } from "next/navigation";
import { allDays, getDay, githubUrl, readme, STAGES } from "@/lib/course";
import { renderLesson } from "@/lib/markdown";
import { Footer, Navbar } from "@/components/Chrome";
import { Checklist } from "@/components/Checklist";
import { CopyButtons } from "@/components/CopyButtons";

type Params = { params: Promise<{ n: string }> };

export const dynamicParams = false; // only the 100 days exist

export function generateStaticParams() {
  return allDays().map((d) => ({ n: String(d.day) }));
}

export async function generateMetadata({ params }: Params): Promise<Metadata> {
  const day = getDay(Number((await params).n));
  if (!day) return {};
  return { title: `Day ${day.day}: ${day.title}`, description: day.objectives.slice(0, 2).join(". ") || `Day ${day.day} of 100 Days of Java.` };
}

// The exact commands to run a folder's tests, as the course README gives them.
const JUNIT = "lib/junit-platform-console-standalone-6.1.3.jar";
function testCommands(dir: string, maven: boolean): string[] {
  if (maven) return [`cd ${dir}`, "mvn test"];
  return [`javac -cp ${JUNIT} -d ${dir} ${dir}/*.java`, `java -jar ${JUNIT} execute -cp ${dir} --scan-classpath`];
}

export default async function DayPage({ params }: Params) {
  const n = Number((await params).n);
  const day = getDay(n);
  if (!day) notFound();
  const stage = STAGES[day.stage - 1];
  const days = allDays();
  const html = renderLesson(readme(n), day.folder);
  const prev = days[n - 2];
  const next = days[n];

  return (
    <div style={{ "--pc": `var(--p${day.stage})` } as React.CSSProperties}>
      <a className="skip" href="#lesson">Skip to the lesson</a>
      <Navbar />
      <header className="page-head">
        <div className="container">
          <ol className="crumbs">
            <li><Link href="/">Home</Link></li>
            <li><Link href="/#path">Stage {stage.n}: {stage.name}</Link></li>
            <li aria-current="page">Day {n}</li>
          </ol>
          <div className="title">
            <div className="badge-day" aria-hidden="true"><small>DAY</small><b>{n}</b></div>
            <div>
              <h1>{day.title}</h1>
              <div className="labels">
                <span className="label">Stage {stage.n} · Day {n - stage.first + 1} of {stage.last - stage.first + 1}</span>
                {day.capstone && <span className="label label-cap">Capstone</span>}
                {day.tests > 0 && <span className="label label-tests">{day.tests} tests</span>}
                {day.maven && <span className="label">Maven</span>}
                {day.javafx && <span className="label">JavaFX</span>}
              </div>
            </div>
          </div>
        </div>
      </header>

      <div className="container">
        <div className="lesson">
          <main id="lesson" className="panel prose" tabIndex={-1} dangerouslySetInnerHTML={{ __html: html }} />
          <CopyButtons containerId="lesson" />
          <aside className="sidebar" aria-label="Today's progress">
            <section className="panel" aria-labelledby="check-title">
              <h2 id="check-title">Today&apos;s checklist</h2>
              <Checklist day={n} items={day.checklist} last={n === 100} />
            </section>
            <section className="panel" aria-labelledby="run-title">
              <h2 id="run-title">{day.testDirs.length > 0 ? "Run the tests" : "Check your work"}</h2>
              {day.testDirs.length > 0 ? (
                <>
                  <p style={{ fontSize: 14, color: "var(--muted)", margin: "0 0 10px" }}>
                    {day.maven ? "From the repository root:" : "From the repository root (no Maven needed yet):"}
                  </p>
                  {day.testDirs.map((dir) => (
                    <div key={dir}>
                      {day.testDirs.length > 1 && <p style={{ fontSize: 13, fontWeight: 700, margin: "0 0 6px" }}>{dir.split("/").slice(1).join("/") || dir}</p>}
                      {testCommands(dir, day.maven).map((c) => <div className="cmd" key={c}>{c}</div>)}
                    </div>
                  ))}
                </>
              ) : (
                <p style={{ fontSize: 15, color: "var(--muted)" }}>This day has no tests yet: use its checklist to judge your work.</p>
              )}
              <ul className="side-links">
                <li><a href={githubUrl(day.folder, false)}>This day&apos;s folder on GitHub</a></li>
                <li><a href={githubUrl(`${day.folder}/README.md`, true)}>This lesson on GitHub</a></li>
              </ul>
            </section>
          </aside>
        </div>
        <nav className="pager" aria-label="Other days">
          {prev && (
            <Link className="prev" href={`/day/${prev.day}/`}>
              <small>← Day {prev.day}</small>
              <strong>{prev.title}</strong>
            </Link>
          )}
          {next && (
            <Link className="next" href={`/day/${next.day}/`}>
              <small>Day {next.day} →</small>
              <strong>{next.title}</strong>
            </Link>
          )}
        </nav>
        <p style={{ height: 48 }} />
      </div>
      <Footer />
    </div>
  );
}
