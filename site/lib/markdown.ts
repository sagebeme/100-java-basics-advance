// A day's README as HTML, at build time: code already highlighted, links pointing somewhere useful.
import { Marked, type Tokens } from "marked";
import hljs from "highlight.js";
import { githubUrl } from "./course";

const escape = (s: string) => s.replace(/[&<>"']/g, (c) => ({ "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;", "'": "&#39;" })[c]!);

// The day's own title is shown in the page header, and its checklist in the sidebar, so neither is
// repeated in the body.
export function lessonBody(md: string): string {
  return md
    .replace(/\r\n/g, "\n")
    .replace(/^# .*\n/, "")
    .replace(/^## ✅ Checklist\n[\s\S]*?(?=^## |(?![\s\S]))/m, "");
}

// A link in a README is relative to its day folder. Another day becomes that day's page on this
// site; any other file or folder in the repo opens on GitHub.
export function resolveLink(href: string, folder: string): string {
  if (/^(https?:|mailto:|#)/.test(href)) return href;
  const url = new URL(href, `https://repo.invalid/${folder}/`);
  const repoPath = decodeURIComponent(url.pathname.slice(1)).replace(/\/$/, "");
  const day = /^day(\d{2,3})(?:\/README\.md)?$/.exec(repoPath);
  if (day) return `/day/${Number(day[1])}/${url.hash}`;
  return githubUrl(repoPath, /\.[a-z0-9]+$/i.test(repoPath)) + url.hash;
}

export function renderLesson(md: string, folder: string): string {
  const marked = new Marked({
    gfm: true,
    renderer: {
      code({ text, lang }: Tokens.Code) {
        const language = (lang ?? "").split(/\s/)[0].toLowerCase();
        const known = language && hljs.getLanguage(language);
        const html = known ? hljs.highlight(text, { language, ignoreIllegals: true }).value : escape(text);
        return `<div class="code"><pre><code class="hljs${known ? ` language-${language}` : ""}">${html}</code></pre></div>\n`;
      },
      link({ href, title, tokens }: Tokens.Link) {
        const text = this.parser.parseInline(tokens);
        const target = resolveLink(href, folder);
        return `<a href="${escape(target)}"${title ? ` title="${escape(title)}"` : ""}>${text}</a>`;
      },
      table(token: Tokens.Table) {
        const cell = (c: Tokens.TableCell, tag: "th" | "td") => `<${tag}>${this.parser.parseInline(c.tokens)}</${tag}>`;
        const head = `<tr>${token.header.map((c) => cell(c, "th")).join("")}</tr>`;
        const body = token.rows.map((row) => `<tr>${row.map((c) => cell(c, "td")).join("")}</tr>`).join("");
        return `<div class="table-wrap"><table><thead>${head}</thead><tbody>${body}</tbody></table></div>\n`;
      },
    },
  });
  return marked.parse(lessonBody(md), { async: false }) as string;
}
