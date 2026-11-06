import React, { useEffect } from 'react';
import { useTheme } from '../context/ThemeContext';

export default function ProjectModal({ project, onClose }) {
  const { isDark } = useTheme();

  const accent   = isDark ? '#D4AF37' : '#16834A';
  const bgCard   = isDark ? '#1B0B29' : '#FFFFFF';
  const border   = isDark ? '#D4AF3740' : '#16834A40';
  const textMain = isDark ? '#FFFFFF' : '#101B14';
  const textSec  = isDark ? '#C9BFD1' : '#53665A';

  // ESC key handler
  useEffect(() => {
    const handler = e => { if (e.key === 'Escape') onClose(); };
    window.addEventListener('keydown', handler);
    return () => window.removeEventListener('keydown', handler);
  }, [onClose]);

  // Lock scroll
  useEffect(() => {
    document.body.style.overflow = 'hidden';
    return () => { document.body.style.overflow = ''; };
  }, []);

  if (!project) return null;

  return (
    <div
      className="modal-backdrop"
      onClick={onClose}
      role="dialog"
      aria-modal="true"
      aria-label={project.title}
    >
      <div
        className="modal-content w-[94vw] max-w-2xl mx-auto rounded-2xl overflow-hidden shadow-2xl flex flex-col"
        style={{
          background: bgCard,
          border: `2px solid ${border}`,
          maxHeight: '88vh',
        }}
        onClick={e => e.stopPropagation()}
      >
        {/* Header */}
        <div
          className="p-4 sm:p-5 flex justify-between items-center shrink-0"
          style={{ borderBottom: `1px solid ${border}`, background: isDark ? '#241132' : '#F5FAF6' }}
        >
          <div className="min-w-0 pr-2">
            <span
              className="text-[11px] sm:text-xs font-mono font-semibold uppercase tracking-wider"
              style={{ color: accent }}
            >
              {project.categoryLabel}
            </span>
            <h3
              className="text-lg sm:text-xl font-bold mt-0.5 truncate"
              style={{ fontFamily: 'Space Grotesk, sans-serif', color: textMain }}
            >
              {project.title}
            </h3>
          </div>
          <button
            onClick={onClose}
            aria-label="Close"
            className="w-9 h-9 rounded-xl flex items-center justify-center shrink-0 transition hover:scale-110"
            style={{ background: isDark ? '#100718' : '#E7F3E9', color: textSec }}
          >
            <i className="fa-solid fa-xmark"></i>
          </button>
        </div>

        {/* Body – scrollable */}
        <div className="p-4 sm:p-6 overflow-y-auto space-y-4 sm:space-y-5 text-sm flex-1">
          {/* Highlight pill */}
          <div
            className="p-3 rounded-xl text-xs font-semibold"
            style={{ background: isDark ? 'rgba(212,175,55,0.1)' : 'rgba(22,131,74,0.08)', color: accent, border: `1px solid ${accent}25` }}
          >
            <i className="fa-solid fa-bolt mr-1.5"></i>
            {project.highlight}
          </div>

          {/* 1. Problem Statement */}
          {project.problemStatement && (
            <div>
              <h4
                className="font-bold text-xs uppercase font-mono tracking-wider mb-1.5 flex items-center gap-1.5"
                style={{ color: accent }}
              >
                <i className="fa-solid fa-triangle-exclamation text-xs"></i>
                Problem Statement
              </h4>
              <p className="leading-relaxed text-xs sm:text-sm" style={{ color: textSec }}>
                {project.problemStatement}
              </p>
            </div>
          )}

          {/* 2. Proposed Solution */}
          {project.proposedSolution && (
            <div>
              <h4
                className="font-bold text-xs uppercase font-mono tracking-wider mb-1.5 flex items-center gap-1.5"
                style={{ color: accent }}
              >
                <i className="fa-solid fa-lightbulb text-xs"></i>
                Proposed Solution
              </h4>
              <p className="leading-relaxed text-xs sm:text-sm" style={{ color: textSec }}>
                {project.proposedSolution}
              </p>
            </div>
          )}

          {/* 3. Architecture */}
          <div>
            <h4
              className="font-bold text-xs uppercase font-mono tracking-wider mb-1.5 flex items-center gap-1.5"
              style={{ color: accent }}
            >
              <i className="fa-solid fa-network-wired text-xs"></i>
              Architecture &amp; System Design
            </h4>
            <p className="leading-relaxed text-xs sm:text-sm" style={{ color: textSec }}>
              {project.architecture || project.fullDesc}
            </p>
          </div>

          {/* 4. Technologies Used */}
          <div>
            <h4
              className="font-bold text-xs uppercase font-mono tracking-wider mb-2 flex items-center gap-1.5"
              style={{ color: accent }}
            >
              <i className="fa-solid fa-code text-xs"></i>
              Technologies Used
            </h4>
            <div className="flex flex-wrap gap-1.5">
              {project.techs.map((tech, idx) => (
                <span
                  key={idx}
                  className="skill-badge px-2.5 py-1 rounded-md text-xs"
                >
                  {tech}
                </span>
              ))}
            </div>
          </div>

          {/* 5. Main Features */}
          {project.bullets?.length > 0 && (
            <div>
              <h4
                className="font-bold text-xs uppercase font-mono tracking-wider mb-2 flex items-center gap-1.5"
                style={{ color: accent }}
              >
                <i className="fa-solid fa-list-check text-xs"></i>
                Main Features
              </h4>
              <ul className="space-y-2">
                {project.bullets.map((b, i) => (
                  <li key={i} className="flex items-start gap-2.5 text-xs sm:text-sm" style={{ color: textSec }}>
                    <i className="fa-solid fa-circle-check mt-1 text-xs shrink-0" style={{ color: accent }}></i>
                    <span>{b}</span>
                  </li>
                ))}
              </ul>
            </div>
          )}

          {/* 6. Real-World Applications */}
          {project.realWorldApplications && (
            <div>
              <h4
                className="font-bold text-xs uppercase font-mono tracking-wider mb-1.5 flex items-center gap-1.5"
                style={{ color: accent }}
              >
                <i className="fa-solid fa-earth-americas text-xs"></i>
                Real-World Applications
              </h4>
              <p className="leading-relaxed text-xs sm:text-sm" style={{ color: textSec }}>
                {project.realWorldApplications}
              </p>
            </div>
          )}

          {/* 7. Project Status */}
          <div>
            <h4
              className="font-bold text-xs uppercase font-mono tracking-wider mb-1.5 flex items-center gap-1.5"
              style={{ color: accent }}
            >
              <i className="fa-solid fa-signal text-xs"></i>
              Project Status
            </h4>
            <span
              className="inline-flex items-center gap-1.5 px-3 py-1.5 rounded-full text-xs font-mono font-semibold"
              style={{
                background: isDark ? 'rgba(212,175,55,0.1)' : 'rgba(22,131,74,0.08)',
                color: accent,
                border: `1px solid ${accent}30`,
              }}
            >
              <span className="w-1.5 h-1.5 rounded-full bg-emerald-400 animate-pulse"></span>
              {project.status || project.year}
            </span>
          </div>
        </div>

        {/* Footer */}
        <div
          className="p-4 flex justify-between items-center gap-3 shrink-0"
          style={{ borderTop: `1px solid ${border}`, background: isDark ? '#241132' : '#F5FAF6' }}
        >
          <a
            href={project.githubUrl}
            target="_blank"
            rel="noopener noreferrer"
            className="inline-flex items-center gap-2 px-4 py-2 rounded-xl text-xs font-semibold transition"
            style={{ background: isDark ? '#100718' : '#E7F3E9', color: accent, border: `1px solid ${accent}30` }}
          >
            <i className="fa-brands fa-github text-sm"></i>
            <span>View on GitHub</span>
          </a>
          <button
            onClick={onClose}
            className="btn-primary px-5 py-2 rounded-xl text-xs font-bold"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
}
