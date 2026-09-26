"use client";
import Link from "next/link";
import { isDone, useProgress } from "@/lib/progress";
import type { DaySummary } from "@/lib/course";

interface StageInfo { n: number; name: string; first: number; last: number; learn: string; ends: string }

// The five stages, written up like a café's chalkboard menu.
export function ChalkMenu({ days, stages }: { days: DaySummary[]; stages: StageInfo[] }) {
  const progress = useProgress();
  return (
    <div className="chalkboard">
      <p className="chalk-title" aria-hidden="true">~ Today&apos;s Menu ~</p>
      <div className="menu">
        {stages.map((s) => {
          const own = days.filter((d) => d.stage === s.n);
          const finished = own.filter((d) => isDone(progress, d.day, d.items)).length;
          return (
            <details key={s.n} className="menu-item">
              <summary>
                <span className="dish">{s.name}</span>
                <span className="leader" aria-hidden="true" />
                <span className="days">Days {s.first}–{s.last}</span>
                <span className="sr-only">, {finished} of {own.length} finished</span>
              </summary>
              <p className="desc">{s.learn}. <em>Served with {s.ends}.</em></p>
              <p className="served" aria-hidden="true">{finished}/{own.length} finished</p>
              <ul>
                {own.map((d) => (
                  <li key={d.day}>
                    <Link href={`/day/${d.day}/`}>
                      <span className="n">{d.day}.</span> {d.title}
                      {d.capstone && <span className="star" aria-label=" (capstone)"> ★</span>}
                      {isDone(progress, d.day, d.items) && <span className="tick" aria-label=" (finished)"> ✓</span>}
                    </Link>
                  </li>
                ))}
              </ul>
            </details>
          );
        })}
      </div>
      <p className="chalk-note" aria-hidden="true">Open a stage to see its days</p>
    </div>
  );
}
