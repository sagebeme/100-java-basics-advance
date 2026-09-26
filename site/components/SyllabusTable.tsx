"use client";
import Link from "next/link";
import { useState } from "react";
import { nextDay, percent, useProgress } from "@/lib/progress";
import type { DaySummary } from "@/lib/course";

interface StageInfo { n: number; name: string; first: number; last: number; learn: string; ends: string }

// Every day by stage, with a search box that filters as you type.
export function SyllabusTable({ days, stages }: { days: DaySummary[]; stages: StageInfo[] }) {
  const [query, setQuery] = useState("");
  const progress = useProgress();
  const next = nextDay(progress, days.map((d) => d.items)) ?? 1;
  const words = query.toLowerCase().split(/\s+/).filter(Boolean);
  const matches = (d: DaySummary) => {
    const text = `${d.day} ${d.title} ${d.brief} ${stages[d.stage - 1].name}`.toLowerCase();
    return words.every((w) => text.includes(w));
  };
  const shown = days.filter(matches);

  return (
    <>
      <div className="toolbar">
        <label className="search">
          <svg viewBox="0 0 24 24" aria-hidden="true"><circle cx="10.5" cy="10.5" r="6.5" /><path d="M16 16l5 5" /></svg>
          <span className="sr-only">Search the days</span>
          <input type="search" value={query} onChange={(e) => setQuery(e.target.value)} placeholder="Search: Spring, JavaFX, game, API…" autoComplete="off" />
        </label>
        <nav className="jump" aria-label="Jump to a stage">
          {stages.map((s) => (
            <a key={s.n} href={`#stage-${s.n}`} title={s.name} aria-label={`Stage ${s.n}: ${s.name}`} style={{ "--pc": `var(--p${s.n})` } as React.CSSProperties}>
              {s.n}
            </a>
          ))}
        </nav>
      </div>
      <p className="sr-only" role="status">{words.length ? `${shown.length} of 100 days match` : ""}</p>

      {stages.map((s) => {
        const rows = shown.filter((d) => d.stage === s.n);
        if (!rows.length) return null;
        return (
          <section key={s.n} className="panel syllabus-phase" id={`stage-${s.n}`} aria-labelledby={`stage-${s.n}-title`} style={{ "--pc": `var(--p${s.n})` } as React.CSSProperties}>
            <div className="top">
              <div>
                <h2 id={`stage-${s.n}-title`}>Stage {s.n}: {s.name}</h2>
                <p>{s.learn}. Ends with {s.ends}.</p>
              </div>
              <small>Days {s.first}–{s.last}</small>
            </div>
            <table className="rows">
              <thead>
                <tr>
                  <th scope="col">Day</th>
                  <th scope="col">Lesson</th>
                  <th scope="col" className="b">You&apos;ll learn</th>
                  <th scope="col"><span className="sr-only">Status</span></th>
                </tr>
              </thead>
              <tbody>
                {rows.map((d) => {
                  const p = percent(progress, d.day, d.items);
                  return (
                    <tr key={d.day} className={d.capstone ? "capstone" : undefined}>
                      <td className="n">Day {d.day}</td>
                      <td className="p">
                        <Link href={`/day/${d.day}/`}>{d.title}</Link>
                        {d.capstone && <> <span className="label label-cap">Capstone</span></>}
                        {d.tests > 0 && <span className="topic">{d.tests} tests</span>}
                      </td>
                      <td className="b">{d.brief}</td>
                      <td className="s">
                        {p === 100 ? <span className="label label-done">Done</span>
                          : d.day === next ? <span className="label label-next">Up next</span>
                          : p > 0 ? <span className="label">{p}%</span> : null}
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </section>
        );
      })}
      {shown.length === 0 && <div className="panel empty">Nothing matches that. Try &ldquo;spring&rdquo;, &ldquo;game&rdquo; or &ldquo;api&rdquo;.</div>}
    </>
  );
}
