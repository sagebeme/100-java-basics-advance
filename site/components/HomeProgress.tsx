"use client";
import Link from "next/link";
import { isDone, nextDay, useProgress } from "@/lib/progress";
import type { DaySummary } from "@/lib/course";

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
