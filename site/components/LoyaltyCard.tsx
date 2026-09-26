"use client";
import Link from "next/link";
import { isDone, nextDay, percent, useProgress } from "@/lib/progress";
import type { DaySummary } from "@/lib/course";

// A café loyalty card with 100 stamps: one per day. Finished days get stamped, the next one is
// ringed, and every stamp is a link to its day.
export function LoyaltyCard({ days }: { days: DaySummary[] }) {
  const progress = useProgress();
  const next = nextDay(progress, days.map((d) => d.items)) ?? 1;
  const done = days.filter((d) => isDone(progress, d.day, d.items)).length;
  return (
    <section className="loyalty" aria-labelledby="loyalty-title">
      <div className="loyalty-head">
        <div>
          <h2 id="loyalty-title">Loyalty card</h2>
          <p>One stamp for every day you finish. Tick a day&apos;s checklist to earn it.</p>
        </div>
        <p className="loyalty-count"><b>{done}</b>/100 stamped</p>
      </div>
      <nav aria-label="All 100 days">
        <ol className="stamps">
          {days.map((d) => {
            const finished = isDone(progress, d.day, d.items);
            const started = !finished && percent(progress, d.day, d.items) > 0;
            const state = finished ? "done" : d.day === next ? "next" : started ? "started" : "";
            const status = finished ? ", stamped" : d.day === next ? ", up next" : "";
            return (
              <li key={d.day}>
                <Link
                  href={`/day/${d.day}/`}
                  className={`stamp ${state}${d.capstone ? " cap" : ""}`}
                  aria-label={`Day ${d.day}: ${d.title}${d.capstone ? " (capstone)" : ""}${status}`}
                  title={`Day ${d.day}: ${d.title}`}
                >
                  {finished ? <span aria-hidden="true">☕</span> : d.day}
                </Link>
              </li>
            );
          })}
        </ol>
      </nav>
      <p className="loyalty-key" aria-hidden="true">
        <span className="stamp done sample">☕</span> finished
        <span className="stamp next sample">{next}</span> up next
        <span className="stamp cap sample">★</span> capstone
      </p>
    </section>
  );
}
