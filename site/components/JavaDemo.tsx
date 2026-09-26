"use client";
import { useRef, useState, type KeyboardEvent } from "react";

// The same bug in Python and in Java. Both outputs are real: they were run to write this.
const DEMOS = {
  python: {
    file: "bill.py",
    code: (
      <>
        <span className="k">def</span> <span className="f">total</span>(price, quantity):{"\n"}
        {"    "}<span className="k">return</span> price * quantity{"\n"}
        {"\n"}
        price = <span className="s">&quot;450&quot;</span>  <span className="c"># from the form</span>{"\n"}
        <span className="f">print</span>(<span className="s">&quot;Total: KES&quot;</span>, <span className="f">total</span>(price, <span className="n">3</span>))
      </>
    ),
    run: "$ python3 bill.py",
    out: "Total: KES 450450450",
    verdict: (
      <>
        Python runs it without a word. The form gave you text, not a number, and <code>&quot;450&quot; * 3</code> repeats the text three times. The customer gets a bill for KES 450,450,450.
      </>
    ),
  },
  java: {
    file: "Bill.java",
    code: (
      <>
        <span className="k">public class</span> <span className="t">Bill</span> {"{"}{"\n"}
        {"    "}<span className="k">static</span> <span className="t">int</span> <span className="f">total</span>(<span className="t">int</span> price, <span className="t">int</span> quantity) {"{"}{"\n"}
        {"        "}<span className="k">return</span> price * quantity;{"\n"}
        {"    }"}{"\n"}
        {"\n"}
        {"    "}<span className="k">public static void</span> <span className="f">main</span>(<span className="t">String</span>[] args) {"{"}{"\n"}
        {"        "}<span className="t">String</span> price = <span className="s">&quot;450&quot;</span>; <span className="c">// from the form</span>{"\n"}
        {"        "}System.out.<span className="f">println</span>(<span className="s">&quot;Total: KES &quot;</span> + <span className="f">total</span>(<span className="squiggle">price</span>, <span className="n">3</span>));{"\n"}
        {"    }"}{"\n"}
        {"}"}
      </>
    ),
    run: "$ javac Bill.java",
    out: "Bill.java:8: error: incompatible types: String cannot be converted to int",
    verdict: (
      <>
        Java won&apos;t even compile it, and points at the exact spot. Turn the text into a number with <code>Integer.parseInt(price)</code> and the bill is KES 1,350. Days 1 and 2 cover this.
      </>
    ),
  },
} as const;

type Key = keyof typeof DEMOS;

export function JavaDemo() {
  const [key, setKey] = useState<Key>("python");
  const tabs = { python: useRef<HTMLButtonElement>(null), java: useRef<HTMLButtonElement>(null) };
  const demo = DEMOS[key];
  const onKey = (e: KeyboardEvent) => {
    if (e.key !== "ArrowLeft" && e.key !== "ArrowRight") return;
    const other: Key = key === "python" ? "java" : "python";
    setKey(other);
    tabs[other].current?.focus();
  };
  return (
    <div className="demo">
      <div>
        <h3>A KES 450 order, times three</h3>
        <p className="verdict">{demo.verdict}</p>
      </div>
      <div>
        <div className="tabs" role="tablist" aria-label="Language" onKeyDown={onKey}>
          {(["python", "java"] as const).map((k) => (
            <button
              key={k}
              ref={tabs[k]}
              type="button"
              role="tab"
              id={`tab-${k}`}
              className={k}
              aria-selected={key === k}
              aria-controls="demo-panel"
              tabIndex={key === k ? 0 : -1}
              onClick={() => setKey(k)}
            >
              {k === "python" ? "Python" : "Java"}
            </button>
          ))}
        </div>
        <div className="window" id="demo-panel" role="tabpanel" aria-labelledby={`tab-${key}`}>
          <div className="bar" aria-hidden="true"><i /><i /><i /><b>{demo.file}</b></div>
          <pre>{demo.code}</pre>
          <div className="out bad" aria-live="polite">{demo.run}<br />{demo.out}</div>
        </div>
      </div>
    </div>
  );
}
