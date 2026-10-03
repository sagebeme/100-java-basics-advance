// The same bug in Python and in Java, side by side: a farmer's pay rate typed into a form. Both
// outputs are real: they were run to write this.
export function JavaDemo() {
  return (
    <div className="versus">
      <figure className="side">
        <figcaption>Python</figcaption>
        <div className="window">
          <div className="bar" aria-hidden="true"><i /><i /><i /><b>pay.py</b></div>
          <pre>
            <span className="k">def</span> <span className="f">pay</span>(rate, kg):{"\n"}
            {"    "}<span className="k">return</span> rate * kg{"\n"}
            {"\n"}
            rate = <span className="s">&quot;85&quot;</span>  <span className="c"># from the form</span>{"\n"}
            <span className="f">print</span>(<span className="s">&quot;Pay: KES&quot;</span>, <span className="f">pay</span>(rate, <span className="n">3</span>))
          </pre>
          <div className="out bad">$ python3 pay.py<br />Pay: KES 858585</div>
        </div>
        <p>Runs without a word. <code>&quot;85&quot; * 3</code> repeats the text three times, and a farmer who picked 3 kg is owed KES 858,585.</p>
      </figure>
      <span className="vs" aria-hidden="true">VS</span>
      <figure className="side">
        <figcaption>Java</figcaption>
        <div className="window">
          <div className="bar" aria-hidden="true"><i /><i /><i /><b>Pay.java</b></div>
          <pre>
            <span className="k">public class</span> <span className="t">Pay</span> {"{"}{"\n"}
            {"    "}<span className="k">static</span> <span className="t">int</span> <span className="f">pay</span>(<span className="t">int</span> rate, <span className="t">int</span> kg) {"{"}{"\n"}
            {"        "}<span className="k">return</span> rate * kg;{"\n"}
            {"    }"}{"\n"}
            {"\n"}
            {"    "}<span className="k">public static void</span> <span className="f">main</span>(<span className="t">String</span>[] args) {"{"}{"\n"}
            {"        "}<span className="t">String</span> rate = <span className="s">&quot;85&quot;</span>; <span className="c">// from the form</span>{"\n"}
            {"        "}System.out.<span className="f">println</span>(<span className="s">&quot;Pay: KES &quot;</span> + <span className="f">pay</span>(<span className="squiggle">rate</span>, <span className="n">3</span>));{"\n"}
            {"    }"}{"\n"}
            {"}"}
          </pre>
          <div className="out bad">$ javac Pay.java<br />Pay.java:8: error: incompatible types: String cannot be converted to int</div>
        </div>
        <p>Won&apos;t even compile, and points at the exact spot. <code>Integer.parseInt(rate)</code> fixes it: KES 255. Days 1 and 2 cover this.</p>
      </figure>
    </div>
  );
}
