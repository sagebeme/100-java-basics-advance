"use client";
import Link from "next/link";
import { percent, setTicks, useProgress } from "@/lib/progress";

// The day's own checklist, saved in this browser.
export function Checklist({ day, items, last }: { day: number; items: string[]; last: boolean }) {
  const progress = useProgress();
  const ticks = progress[String(day)] ?? [];
  const pct = percent(progress, day, items.length);
  const toggle = (i: number, checked: boolean) => {
    const next = items.map((_, j) => (j === i ? checked : Boolean(ticks[j])));
    setTicks(day, next);
  };
  return (
    <>
      <ul className="checklist">
        {items.map((item, i) => (
          <li key={i}>
            <label>
              <input type="checkbox" checked={Boolean(ticks[i])} onChange={(e) => toggle(i, e.target.checked)} />
              <span>{item}</span>
            </label>
          </li>
        ))}
      </ul>
      <div className="meter-row">
        <div className="progress" role="progressbar" aria-label="Today's progress" aria-valuemin={0} aria-valuemax={100} aria-valuenow={pct}>
          <span style={{ width: `${pct}%` }} />
        </div>
        <span>{pct}%</span>
      </div>
      {pct === 100 && (
        <p className="done-msg" role="status">
          {last ? "That's all 100 days. You did it. 🏆" : <>Day {day} done! 🎉 <Link href={`/day/${day + 1}/`}>On to Day {day + 1} →</Link></>}
        </p>
      )}
    </>
  );
}
