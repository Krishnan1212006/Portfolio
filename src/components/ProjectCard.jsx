import React from 'react';
import { useTheme } from '../context/ThemeContext';

export default function ProjectCard({ project, onOpenDetails }) {
  const { isDark } = useTheme();

  const accent      = isDark ? '#D4AF37' : '#16834A';
  const bgCard      = isDark ? '#241132' : '#FFFFFF';
  const bgStat      = isDark ? 'rgba(84,36,119,0.2)' : 'rgba(22,131,74,0.08)';
  const borderCard  = isDark ? 'rgba(212,175,55,0.2)' : 'rgba(22,131,74,0.2)';
  const textMain    = isDark ? '#FFFFFF' : '#101B14';
  const textSec     = isDark ? '#C9BFD1' : '#53665A';

  const catColors = {
    cybersecurity: isDark ? { bg: 'rgba(84,36,119,0.4)', text: '#D4AF37', border: '#D4AF3730' }
                          : { bg: 'rgba(22,131,74,0.1)', text: '#16834A', border: '#16834A30' },
    iot:          isDark ? { bg: 'rgba(14,116,144,0.3)', text: '#67e8f9', border: '#67e8f920' }
                          : { bg: 'rgba(22,131,74,0.1)', text: '#059669', border: '#05966920' },
    design:       isDark ? { bg: 'rgba(219,39,119,0.2)', text: '#f9a8d4', border: '#f9a8d420' }
                          : { bg: 'rgba(219,39,119,0.08)', text: '#db2777', border: '#db277720' },
    default:      isDark ? { bg: 'rgba(100,100,100,0.2)', text: '#C9BFD1', border: '#C9BFD120' }
                          : { bg: 'rgba(100,100,100,0.08)', text: '#53665A', border: '#53665A20' },
  };
  const cc = catColors[project.category] || catColors.default;

  return (
    <div
      className="themed-card rounded-2xl overflow-hidden flex flex-col h-full"
      style={{ background: bgCard, border: `1px solid ${borderCard}` }}
    >
      {/* Banner */}
      <div
        className="h-40 relative flex flex-col justify-between p-5 overflow-hidden"
        style={{ background: isDark ? 'linear-gradient(135deg, #1B0B29 0%, #241132 100%)' : 'linear-gradient(135deg, #E7F3E9 0%, #F5FAF6 100%)' }}
      >
        <div
          className="absolute -right-8 -bottom-8 w-32 h-32 rounded-full blur-2xl pointer-events-none"
          style={{ background: isDark ? 'rgba(212,175,55,0.08)' : 'rgba(22,131,74,0.08)' }}
        />

        <div className="flex items-center justify-between z-10">
          <span
            className="px-2.5 py-1 rounded-md text-[11px] font-mono font-bold uppercase"
            style={{ background: cc.bg, color: cc.text, border: `1px solid ${cc.border}` }}
          >
            {project.categoryLabel}
          </span>
          <span className="text-xs font-mono" style={{ color: textSec }}>{project.year}</span>
        </div>

        <div className="z-10 flex items-center gap-3">
          <div
            className="w-11 h-11 rounded-xl flex items-center justify-center text-xl"
            style={{ background: isDark ? 'rgba(212,175,55,0.15)' : 'rgba(22,131,74,0.12)', color: accent }}
          >
            <i className={`fa-solid ${project.icon}`}></i>
          </div>
          <div>
            <div className="text-[11px] font-mono font-semibold uppercase opacity-80" style={{ color: textSec }}>
              {project.subtitle}
            </div>
            <div className="text-base font-bold leading-tight" style={{ color: textMain, fontFamily: 'Space Grotesk, sans-serif' }}>
              {project.title}
            </div>
          </div>
        </div>
      </div>

      {/* Content */}
      <div className="p-5 space-y-3 flex-1">
        <div
          className="inline-flex items-center gap-1.5 px-2.5 py-1 rounded-md text-xs font-semibold"
          style={{ background: isDark ? 'rgba(212,175,55,0.1)' : 'rgba(22,131,74,0.08)', color: accent, border: `1px solid ${accent}25` }}
        >
          <i className="fa-solid fa-bolt text-[10px]"></i>
          <span>{project.highlight}</span>
        </div>

        <p className="text-sm leading-relaxed" style={{ color: textSec }}>
          {project.shortDesc}
        </p>

        {/* Tech Stack */}
        <div className="flex flex-wrap gap-1.5">
          {project.techs.slice(0, 6).map((tech, i) => (
            <span
              key={i}
              className="skill-badge px-2 py-0.5 rounded text-xs"
            >
              {tech}
            </span>
          ))}
          {project.techs.length > 6 && (
            <span className="px-2 py-0.5 rounded text-xs" style={{ color: accent, fontFamily: 'JetBrains Mono, monospace' }}>
              +{project.techs.length - 6} more
            </span>
          )}
        </div>
      </div>

      {/* Footer Actions */}
      <div
        className="p-4 flex items-center gap-2"
        style={{ borderTop: `1px solid ${borderCard}` }}
      >
        <button
          onClick={() => onOpenDetails(project)}
          className="flex-1 py-2 px-3 rounded-xl text-xs font-semibold transition flex items-center justify-center gap-1.5"
          style={{ background: bgStat, color: textMain, border: `1px solid ${borderCard}` }}
          onMouseEnter={e => { e.currentTarget.style.borderColor = accent; e.currentTarget.style.color = accent; }}
          onMouseLeave={e => { e.currentTarget.style.borderColor = borderCard; e.currentTarget.style.color = textMain; }}
        >
          <span>View Details</span>
          <i className="fa-solid fa-arrow-up-right-from-square text-[10px]"></i>
        </button>

        <a
          href={project.githubUrl}
          target="_blank"
          rel="noopener noreferrer"
          title="View on GitHub"
          className="py-2 px-3 rounded-xl text-xs font-semibold transition flex items-center justify-center gap-1.5"
          style={{ background: isDark ? '#100718' : '#F5FAF6', color: accent, border: `1px solid ${accent}30` }}
          onMouseEnter={e => { e.currentTarget.style.borderColor = accent; }}
          onMouseLeave={e => { e.currentTarget.style.borderColor = `${accent}30`; }}
        >
          <i className="fa-brands fa-github text-sm"></i>
          <span>Code</span>
        </a>
      </div>
    </div>
  );
}
