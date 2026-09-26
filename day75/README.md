# Day 75 - Charts and Data Visualization

## 📚 Learning Objectives
- Create advanced charts
- Build interactive visualizations
- Generate multiple chart types
- Export visualizations
- Create comprehensive dashboards

## 🎯 Topics Covered
- Advanced charting
- Multiple chart types
- Interactive features
- Export functionality
- Dashboard design

## 📝 Step-by-Step Instructions

### Step 1: Multiple Chart Types
Create various charts:

```java
public class ChartCreator {
    public JFreeChart createLineChart() { /* ... */ }
    public JFreeChart createPieChart() { /* ... */ }
    public JFreeChart createAreaChart() { /* ... */ }
}
```

## 🎮 Project: Comprehensive Dashboard

### Requirements
Create dashboard with:
1. Multiple chart types
2. Interactive features
3. Data filtering
4. Export options

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** Run from IDE or `mvn exec:java`.

## ✅ Checklist
- [ ] Can create multiple chart types
- [ ] Can build dashboards
- [ ] Can add interactivity
- [ ] Completed dashboard
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Dashboard.java` | One dataset shown as bar, line, pie and area charts, filtered, and exported to PNG with JFreeChart |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day75
mvn compile exec:java
```

Or run the main class from your IDE.

## 🧪 How to Test

```bash
cd day75
mvn test
```

Runs `DashboardTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 76?** You'll learn collections and data structures!
