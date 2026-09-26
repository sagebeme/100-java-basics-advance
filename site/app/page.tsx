import Link from "next/link";
import { STAGES, summaries, totals } from "@/lib/course";
import { Footer, Navbar } from "@/components/Chrome";
import { JavaDemo } from "@/components/JavaDemo";
import { Resume, StartButton } from "@/components/HomeProgress";
import { LoyaltyCard } from "@/components/LoyaltyCard";
import { ChalkMenu } from "@/components/ChalkMenu";

const BREW = [
  { n: 1, name: "Grind", what: "Read the lesson: the objectives, the key ideas, and the steps with their code." },
  { n: 2, name: "Brew", what: "Write the code: small exercises first, then the day's project." },
  { n: 3, name: "Taste", what: "Run the tests: JUnit from Day 1, then mvn test from Day 54." },
  { n: 4, name: "Serve", what: "Tick off the checklist, commit to Git, and collect your stamp." },
];

export default function Home() {
  const days = summaries();
  const { tests, capstones } = totals();
  const stages = STAGES.map((s) => ({ ...s }));

  return (
    <>
      <a className="skip" href="#main">Skip to content</a>
      <Navbar />
      <main id="main" className="java-home">
        <header className="roast">
          <div className="container">
            <p className="banner"><span>100 lessons · 5 stages · an hour a day</span></p>
            <h1>
              <span className="script">One cup a day</span>
              100 Days of Java
            </h1>
            <p className="lede">
              From your first variable to Spring Boot web apps, a database, Docker, and a machine learning model you wrote yourself. Most days come with tests that tell you when it works.
            </p>
            <div className="actions">
              <StartButton days={days} className="btn btn-gloss" />
              <a className="btn btn-ghost" href="#path">See the menu</a>
            </div>
            <Resume days={days} />
          </div>
        </header>

        <div className="container">
          <LoyaltyCard days={days} />
        </div>

        <section className="band" id="demo" aria-labelledby="demo-title">
          <div className="container">
            <div className="section-head">
              <span className="over">Why Java?</span>
              <h2 id="demo-title">Catch the bug before your customers do</h2>
              <p>This course follows the famous Python &ldquo;100 Days of Code&rdquo;. Here&apos;s the same small bug in both.</p>
            </div>
            <JavaDemo />
          </div>
        </section>

        <section className="band white" id="how" aria-labelledby="how-title">
          <div className="container">
            <div className="section-head">
              <span className="over">How each day works</span>
              <h2 id="how-title">The daily brew</h2>
              <p>Every day is a folder with a lesson, exercises and a project. {tests.toLocaleString("en-US")} tests check your work along the way.</p>
            </div>
            <ol className="brew">
              {BREW.map((step) => (
                <li key={step.n}>
                  <span className="bean" aria-hidden="true">{step.n}</span>
                  <h3>{step.name}</h3>
                  <p>{step.what}</p>
                </li>
              ))}
            </ol>
          </div>
        </section>

        <section className="band board-band" id="path" aria-labelledby="path-title">
          <div className="container">
            <div className="section-head">
              <span className="over">Your path</span>
              <h2 id="path-title">Five stages, one at a time</h2>
              <p>Plain Java until Day 53. Maven and Spring Boot from Day 54. Then twenty portfolio projects, with {capstones} capstones along the way.</p>
            </div>
            <ChalkMenu days={days} stages={stages} />
          </div>
        </section>

        <section className="band white" id="need" aria-labelledby="need-title">
          <div className="container">
            <div className="section-head">
              <span className="over">Before you start</span>
              <h2 id="need-title">What you&apos;ll need</h2>
            </div>
            <div className="receipt">
              <p className="receipt-head">100 DAYS OF JAVA<br /><small>ORDER #100 · TABLE FOR ONE</small></p>
              <table>
                <caption className="sr-only">What you&apos;ll need</caption>
                <tbody>
                  <tr><td>1 × JDK 21</td><td>Eclipse Temurin</td></tr>
                  <tr><td>1 × Editor</td><td>IntelliJ or VS Code</td></tr>
                  <tr><td>1 × Git</td><td>to save your work</td></tr>
                  <tr><td>1 × Maven</td><td>from Day 54</td></tr>
                  <tr><td>100 × Lessons</td><td>with exercises</td></tr>
                  <tr className="total"><td>Total</td><td>1 hr a day</td></tr>
                </tbody>
              </table>
              <p className="receipt-foot">No experience needed · <Link href="/syllabus/#setup">Setup steps</Link></p>
            </div>
          </div>
        </section>

        <section className="cta" aria-labelledby="cta-title">
          <div className="container">
            <h2 id="cta-title">Your first cup takes about an hour</h2>
            <p>Day 1 is variables: storing and changing data. By Day 100 your loyalty card is full, and so is your portfolio.</p>
            <StartButton days={days} className="btn btn-sun" />
          </div>
        </section>
      </main>
      <Footer full />
    </>
  );
}
