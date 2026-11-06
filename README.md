# Hari Krishnan.s — Developer Portfolio (React.js)

An interactive, responsive developer portfolio web application built with **React 19**, **Vite**, and **Tailwind CSS**, showcasing projects in cybersecurity research, embedded IoT systems, real-time threat telemetry, and software engineering.

---

## 🌟 Key Features

- **Component-Driven React Architecture**: Modular components for navbar, hero, project grid, live telemetry simulators, modal breakdowns, experience, and contact forms.
- **Dynamic State Management**:
  - Real-time instant project search and category filtering.
  - Interactive deep-dive modal dialogs with keyboard accessibility (ESC key dismissal).
- **Centralized Data Model**: All portfolio data, project specifications, career milestones, awards, and skills stored cleanly in `src/data/portfolioData.js`.
- **Assets & Downloads**: Profile photography, social Open Graph banners, browser favicon, and verified PDF resume downloads.

---

## 📁 React Project Structure

```text
Portfolio Website/
├── index.html                   # HTML entry point (mounts React root)
├── package.json                 # Dependencies (React 19, Vite, Tailwind)
├── vite.config.js               # Vite + React plugin configuration
├── README.md                    # Project documentation
├── .gitignore                   # Standard web ignores
│
├── assets/                      # Static raw assets (images & PDF documents)
├── public/                      # Static assets copied directly to build output
│
└── src/
    ├── main.jsx                 # Mounts App to document root
    ├── App.jsx                  # Root component & global state coordination
    ├── index.css                # Mesh gradients, glassmorphism, scrollbars
    │
    ├── data/
    │   └── portfolioData.js     # Single source of truth for all portfolio content
    │
    └── components/
        ├── Navbar.jsx           # Sticky glass header & mobile drawer
        ├── Hero.jsx             # Headline, credentials, CTAs, metrics card
        ├── ProjectCard.jsx      # Reusable project card with status badges & architecture links
        ├── ProjectGrid.jsx      # Filter buttons, search bar & responsive grid
        ├── ProjectModal.jsx     # Full-screen architectural deep-dive dialog
        ├── Experience.jsx       # Internship & team leadership history
        ├── Skills.jsx           # Categorized capability cards
        ├── AcademicsAwards.jsx  # Education degrees, certifications & prizes
        ├── Contact.jsx          # Resume download card & interactive form
        └── Footer.jsx           # Branding, back-to-top & social links
```

---

## 🚀 How to Run Locally

### 1. Install Dependencies
```bash
npm install
```

### 2. Start the Development Server
```bash
npm run dev
```
Navigate to `http://localhost:3000` to view the live app with Hot Module Replacement (HMR).

---

## 🏗️ Production Build

To compile and bundle for production:
```bash
npm run build
```
This generates an optimized static bundle in `dist/`.

To preview the production build locally:
```bash
npm run preview
```

---

## 🌐 Deployment

The output in `dist/` is 100% static and ready for instant deployment to:
- **Vercel**: Import repository or run `npx vercel`.
- **Netlify**: Connect your GitHub repo or drag-and-drop the `dist` folder.
- **GitHub Pages**: Build with `npm run build` and deploy the `dist` folder.
- **Cloudflare Pages**: Set build command `npm run build` and output folder `dist`.

---

## 👤 Author & Contact

- **Hari Krishnan.s**
- **Location**: Tirupur, Tamil Nadu, India
- **Email**: [harikrishnansenthil79@gmail.com](mailto:harikrishnansenthil79@gmail.com)
- **Phone**: [+91 76392 94309](tel:+917639294309)
- **GitHub**: [@Krishnan1212006](https://github.com/Krishnan1212006)
- **LinkedIn**: [harikrishnan](https://linkedin.com/in/hari-krishnan-031b46284)
