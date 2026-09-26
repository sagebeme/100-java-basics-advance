// The same bug in Python and in Java, side by side. Both outputs are real: they were run to write
// this.
export function JavaDemo() {
  return (
    <div className="versus">
      <figure className="side">
        <figcaption>Python</figcaption>
        <div className="window">
          <div className="bar" aria-hidden="true"><i /><i /><i /><b>bill.py</b></div>
          <pre>
            <span className="k">def</span> <span className="f">total</span>(price, quantity):{"\n"}
            {"    "}<span className="k">return</span> price * quantity{"\n"}
            {"\n"}
            price = <span className="s">&quot;450&quot;</span>  <span className="c"># from the form</span>{"\n"}
            <span className="f">print</span>(<span className="s">&quot;Total: KES&quot;</span>, <span className="f">total</span>(price, <span className="n">3</span>))
          </pre>
          <div className="out bad">$ python3 bill.py<br />Total: KES 450450450</div>
        </div>
        <p>Runs without a word. <code>&quot;450&quot; * 3</code> repeats the text three times, and the customer is billed KES 450,450,450.</p>
      </figure>
      <span className="vs" aria-hidden="true">VS</span>
      <figure className="side">
        <figcaption>Java</figcaption>
        <div className="window">
          <div className="bar" aria-hidden="true"><i /><i /><i /><b>Bill.java</b></div>
          <pre>
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
          </pre>
          <div className="out bad">$ javac Bill.java<br />Bill.java:8: error: incompatible types: String cannot be converted to int</div>
        </div>
        <p>Won&apos;t even compile, and points at the exact spot. <code>Integer.parseInt(price)</code> fixes it: KES 1,350. Days 1 and 2 cover this.</p>
      </figure>
    </div>
  );
}
