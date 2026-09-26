"use client";
import Link from "next/link";
import { isDone, nextDay, percent, useProgress } from "@/lib/progress";
import type { DaySummary } from "@/lib/course";

interface StageInfo {
  n: number;
  name: string;
  first: number;
  last: number;
  learn: string;
  ends: string;
}

// "Start Day 1" until you've ticked something, then "Continue with Day N".
export function StartButton({ days, className }: { days: DaySummary[]; className: string }) {
  const progress = useProgress();
  const next = nextDay(progress, days.map((d) => d.items));
  return (
    <Link className={className} href={`/day/${next ?? 1}/`}>
      {next ? `Continue with Day ${next} →` : "Start Day 1 →"}
    </Link>
  );
}

export function Resume({ days }: { days: DaySummary[] }) {
  const progress = useProgress();
  const next = nextDay(progress, days.map((d) => d.items));
  if (!next) return null;
  const done = days.filter((d) => isDone(progress, d.day, d.items)).length;
  return (
    <p className="resume">
      {done === days.length
        ? "You've finished all 100 days. Legend."
        : `Welcome back. You've finished ${done} of 100 days. Up next: Day ${next}, ${days[next - 1].title}.`}
    </p>
  );
}

export function Timeline({ days, stages }: { days: DaySummary[]; stages: StageInfo[] }) {
  const progress = useProgress();
  const next = nextDay(progress, days.map((d) => d.items));
  const current = next ? days[next - 1].stage : 1;
  return (
    <ol className="timeline">
      {stages.map((stage) => {
        const own = days.filter((d) => d.stage === stage.n);
        const finished = own.filter((d) => isDone(progress, d.day, d.items)).length;
        const here = stage.n === current;
        return (
          <li key={stage.n} className="phase" style={{ "--pc": `var(--p${stage.n})` } as React.CSSProperties}>
            <span className="node" aria-hidden="true">{stage.n}</span>
            <div className="card">
              {here && <span className="here" aria-hidden="true">{next ? "You are here" : "Start here"}</span>}
              <div className="top">
                <small>Stage {stage.n} · Days {stage.first}–{stage.last}</small>
                <h3>{stage.name}</h3>
              </div>
              <div className="body">
                <p><b>You learn:</b> {stage.learn}</p>
                <p><b>Ends with:</b> {stage.ends}</p>
                <div className="meter">
                  <div className="progress" role="progressbar" aria-label={`Stage ${stage.n} progress`} aria-valuemin={0} aria-valuemax={own.length} aria-valuenow={finished}>
                    <span style={{ width: `${Math.round((finished / own.length) * 100)}%` }} />
                  </div>
                  {finished}/{own.length} done
                </div>
                <details open={here}>
                  <summary>Days {stage.first}–{stage.last}</summary>
                  <ul className="day-list">
                    {own.map((d) => {
                      const p = percent(progress, d.day, d.items);
                      return (
                        <li key={d.day}>
                          <Link href={`/day/${d.day}/`}>
                            <span className="n">Day {d.day}</span>
                            <span className="t">{d.title}</span>
                            {p === 100 ? <span className="label label-done">Done</span>
                              : d.day === next ? <span className="label label-next">Up next</span>
                              : d.capstone ? <span className="label label-cap">Capstone</span>
                              : p > 0 ? <span className="label">{p}%</span> : null}
                          </Link>
                        </li>
                      );
                    })}
                  </ul>
                </details>
              </div>
            </div>
          </li>
        );
      })}
    </ol>
  );
}
