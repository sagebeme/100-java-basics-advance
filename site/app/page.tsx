import Link from "next/link";
import { STAGES, summaries, totals } from "@/lib/course";
import { Footer, Navbar } from "@/components/Chrome";
import { JavaDemo } from "@/components/JavaDemo";
import { Resume, StartButton, Timeline } from "@/components/HomeProgress";

export default function Home() {
  const days = summaries();
  const { tests, capstones } = totals();
  const stages = STAGES.map((s) => ({ ...s }));

  return (
    <>
      <a className="skip" href="#main">Skip to content</a>
      <Navbar />
      <main id="main">
        <header className="hero">
          <div className="container">
            <div>
              <span className="kicker">Free · 100 lessons · about an hour a day</span>
              <h1>
                <span className="script">One cup a day</span>100 Days of Java
              </h1>
              <p className="lede">
                Start with your first variable. Finish with Spring Boot web apps, a database, Docker, and a machine learning model you wrote yourself. Most days come with tests that tell you when it works.
              </p>
              <div className="actions">
                <StartButton days={days} className="btn btn-sun" />
                <a className="btn btn-ghost" href="#path">See the path</a>
              </div>
              <Resume days={days} />
            </div>
            <div className="window" aria-hidden="true">
              <div className="bar"><i /><i /><i /><b>day01/Main.java</b></div>
              <pre>
                <span className="c">{"// Day 1: your first Java program"}</span>{"\n"}
                <span className="k">public class</span> <span className="t">Main</span> {"{"}{"\n"}
                {"    "}<span className="k">public static void</span> <span className="f">main</span>(<span className="t">String</span>[] args) {"{"}{"\n"}
                {"        "}<span className="t">String</span> name = <span className="s">&quot;Amina&quot;</span>;{"\n"}
                {"        "}<span className="t">int</span> age = <span className="n">21</span>;{"\n"}
                {"        "}System.out.<span className="f">println</span>(name + <span className="s">&quot; is &quot;</span> + age);{"\n"}
                {"    }"}{"\n"}
                {"}"}
              </pre>
              <div className="out good">$ javac Main.java &amp;&amp; java Main<br />Amina is 21</div>
            </div>
          </div>
        </header>

        <section className="stats" aria-label="The course in numbers">
          <ul className="container">
            <li><b>100</b><span>Lessons</span></li>
            <li><b>5</b><span>Stages</span></li>
            <li><b>{tests.toLocaleString("en-US")}</b><span>Tests</span></li>
            <li><b>{capstones}</b><span>Capstones</span></li>
          </ul>
        </section>

        <section className="band" id="demo" aria-labelledby="demo-title">
          <div className="container">
            <div className="section-head">
              <span className="over">Why Java?</span>
              <h2 id="demo-title">Catch the bug before your customers do</h2>
              <p>This course follows the famous Python &ldquo;100 Days of Code&rdquo;. Here&apos;s one thing Java does differently.</p>
            </div>
            <JavaDemo />
          </div>
        </section>

        <section className="band white" id="how" aria-labelledby="how-title">
          <div className="container">
            <div className="section-head">
              <span className="over">How each day works</span>
              <h2 id="how-title">Four steps. Every day.</h2>
              <p>Every day is a folder with a lesson, exercises, and a project.</p>
            </div>
            <ul className="features">
              <li>
                <div className="icon" style={{ "--c": "#16a085" } as React.CSSProperties}>
                  <svg viewBox="0 0 24 24" aria-hidden="true"><path d="M6 3h9l4 4v14H6z" /><path d="M15 3v4h4M9 12h7M9 16h7" /></svg>
                </div>
                <h3>Read the lesson</h3>
                <p>The objectives, the key ideas and the steps, with the code to go with them.</p>
              </li>
              <li>
                <div className="icon" style={{ "--c": "#2980b9" } as React.CSSProperties}>
                  <svg viewBox="0 0 24 24" aria-hidden="true"><path d="M8 7l-5 5 5 5M16 7l5 5-5 5M13.5 5l-3 14" /></svg>
                </div>
                <h3>Write the code</h3>
                <p>Small exercises first, then the day&apos;s project. Days 1–31 give you a <code>_start/</code> folder to begin from.</p>
              </li>
              <li>
                <div className="icon" style={{ "--c": "#8e44ad" } as React.CSSProperties}>
                  <svg viewBox="0 0 24 24" aria-hidden="true"><path d="M4 5h16v14H4z" /><path d="M7 10l3 2-3 2M12 15h5" /></svg>
                </div>
                <h3>Run the tests</h3>
                <p>JUnit tests for your code: a one-line command early on, then <code>mvn test</code> from Day 54.</p>
              </li>
              <li>
                <div className="icon" style={{ "--c": "#d35400" } as React.CSSProperties}>
                  <svg viewBox="0 0 24 24" aria-hidden="true"><path d="M4 12l5 5L20 6" /></svg>
                </div>
                <h3>Tick it off</h3>
                <p>Each day&apos;s own checklist is saved in this browser, so tomorrow you pick up where you left off.</p>
              </li>
            </ul>
          </div>
        </section>

        <section className="band" id="path" aria-labelledby="path-title">
          <div className="container">
            <div className="section-head">
              <span className="over">Your path</span>
              <h2 id="path-title">Five stages, one at a time</h2>
              <p>Plain Java until Day 53. Maven and Spring Boot from Day 54. Then twenty portfolio projects to show what you can do.</p>
            </div>
            <Timeline days={days} stages={stages} />
          </div>
        </section>

        <section className="band white" id="need" aria-labelledby="need-title">
          <div className="container">
            <div className="section-head">
              <span className="over">What it costs</span>
              <h2 id="need-title">Pick your plan</h2>
              <p>There&apos;s only one plan. It&apos;s free.</p>
            </div>
            <div className="pricing">
              <div className="plan">
                <div className="head"><h3>Your setup</h3><div className="price">$0</div></div>
                <ul>
                  <li>JDK 21 (Temurin is free)</li>
                  <li>IntelliJ IDEA Community or VS Code</li>
                  <li>Maven, from Day 54</li>
                </ul>
                <div className="foot"><Link href="/syllabus/#setup">Setup steps</Link></div>
              </div>
              <div className="plan featured">
                <span className="popular" aria-hidden="true">Most popular</span>
                <div className="head"><h3>The course</h3><div className="price">$0 <small>/ forever</small></div></div>
                <ul>
                  <li>100 lessons with exercises</li>
                  <li>{tests.toLocaleString("en-US")} tests to check your work</li>
                  <li>{capstones} capstones, 20 portfolio projects</li>
                  <li>Progress saved as you go</li>
                </ul>
                <div className="foot"><StartButton days={days} className="btn btn-blue" /></div>
              </div>
              <div className="plan">
                <div className="head"><h3>Your time</h3><div className="price">1 hr <small>/ day</small></div></div>
                <ul>
                  <li>No experience needed</li>
                  <li>Console, then GUIs, then the web</li>
                  <li>Then databases, Docker and ML</li>
                </ul>
                <div className="foot"><Link href="/syllabus/">See all 100 days</Link></div>
              </div>
            </div>
          </div>
        </section>

        <section className="cta" aria-labelledby="cta-title">
          <div className="container">
            <h2 id="cta-title">Your first day takes about an hour</h2>
            <p>Day 1 is variables: storing and changing data. By Day 100 you&apos;ll have a portfolio full of things you built.</p>
            <StartButton days={days} className="btn btn-sun" />
          </div>
        </section>
      </main>
      <Footer full />
    </>
  );
}
