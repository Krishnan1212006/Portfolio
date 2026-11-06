import React, { useState } from 'react';
import { ThemeProvider, useTheme } from './context/ThemeContext';
import CyberBackground from './components/CyberBackground';
import Navbar from './components/Navbar';
import Hero from './components/Hero';
import ProjectGrid from './components/ProjectGrid';
import Experience from './components/Experience';
import Skills from './components/Skills';
import AcademicsAwards from './components/AcademicsAwards';
import Contact from './components/Contact';
import Footer from './components/Footer';
import ProjectModal from './components/ProjectModal';

function AppContent() {
  const [activeModalProject, setActiveModalProject] = useState(null);
  const { isDark } = useTheme();

  return (
    <div
      className="min-h-screen antialiased"
      style={{
        backgroundColor: 'var(--bg-main)',
        color: 'var(--text-main)',
        transition: 'background-color 0.4s ease, color 0.4s ease',
      }}
    >
      {/* Animated cybersecurity canvas background */}
      <CyberBackground />

      <Navbar />
      <main>
        <Hero />

        {/* Section separator */}
        <div className="section-sep" />

        <ProjectGrid onOpenDetails={project => setActiveModalProject(project)} />

        <div className="section-sep" />

        <Experience />

        <div className="section-sep" />

        <Skills />

        <div className="section-sep" />

        <AcademicsAwards />

        <div className="section-sep" />

        <Contact />
      </main>

      <Footer />

      {/* Project detail modal */}
      {activeModalProject && (
        <ProjectModal
          project={activeModalProject}
          onClose={() => setActiveModalProject(null)}
        />
      )}
    </div>
  );
}

export default function App() {
  return (
    <ThemeProvider>
      <AppContent />
    </ThemeProvider>
  );
}
