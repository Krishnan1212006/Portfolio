import React, { useState, useMemo } from 'react';
import { useTheme } from '../context/ThemeContext';
import { projectsData } from '../data/portfolioData';
import ProjectCard from './ProjectCard';

const FILTERS = [
  { key: 'all',           label: 'All'             },
  { key: 'cybersecurity', label: 'Cybersecurity'   },
  { key: 'iot',           label: 'IoT'             },
  { key: 'ai-ml',         label: 'AI & ML'         },
  { key: 'web',           label: 'Web Development' },
];

export default function ProjectGrid({ onOpenDetails }) {
  const { isDark } = useTheme();
  const [activeCategory, setActiveCategory] = useState('all');
  const [searchQuery, setSearchQuery] = useState('');

  const accent   = isDark ? '#D4AF37' : '#16834A';
  const bgSec    = isDark ? '#1B0B29' : '#E7F3E9';
  const bgCard   = isDark ? '#241132' : '#FFFFFF';
  const border   = isDark ? 'rgba(212,175,55,0.2)' : 'rgba(22,131,74,0.2)';
  const textMain = isDark ? '#FFFFFF' : '#101B14';
  const textSec  = isDark ? '#C9BFD1' : '#53665A';

  const filteredProjects = useMemo(() => {
    return projectsData.filter(p => {
      const matchesCat =
        activeCategory === 'all' ||
        p.category === activeCategory ||
        (p.categories && p.categories.includes(activeCategory));
      if (!matchesCat) return false;
      if (!searchQuery.trim()) return true;
      const q = searchQuery.toLowerCase().trim();
      return `${p.title} ${p.subtitle} ${p.shortDesc} ${p.highlight} ${p.techs.join(' ')}`.toLowerCase().includes(q);
    });
  }, [activeCategory, searchQuery]);

  return (
    <section id="projects" className="py-16 relative" style={{ borderTop: `1px solid ${accent}20` }}>
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">

        {/* Section Header */}
        <div className="flex flex-col md:flex-row md:items-end justify-between mb-8 gap-4">
          <div>
            <div className="section-label inline-flex items-center gap-2 mb-2">
              <i className="fa-solid fa-layer-group"></i> Portfolio Showcase
            </div>
            <h2 className="section-title text-3xl sm:text-4xl font-extrabold tracking-tight">
              Featured Projects &amp; Engineering Work
            </h2>
            <p className="text-sm sm:text-base mt-1 max-w-2xl" style={{ color: textSec }}>
              Real-world systems spanning AI threat intelligence, certified storage sanitization, embedded satellite telemetry, and IoT hardware.
            </p>
          </div>

          {/* Search */}
          <div className="w-full md:w-80 relative">
            <input
              type="text"
              value={searchQuery}
              onChange={e => setSearchQuery(e.target.value)}
              placeholder="Filter by Python, NIST, IoT, AI…"
              className="form-input w-full rounded-xl px-4 py-2.5 pl-10 text-sm"
              style={{ borderRadius: '0.75rem' }}
            />
            <i className="fa-solid fa-magnifying-glass absolute left-3.5 top-3.5 text-sm" style={{ color: textSec }}></i>
          </div>
        </div>

        {/* Category Filter Pills */}
        <div className="flex flex-wrap items-center gap-2 mb-10 pb-4" style={{ borderBottom: `1px solid ${accent}15` }}>
          {FILTERS.map(f => {
            const count = f.key === 'all'
              ? projectsData.length
              : projectsData.filter(p => p.category === f.key || (p.categories && p.categories.includes(f.key))).length;
            return (
              <button
                key={f.key}
                onClick={() => setActiveCategory(f.key)}
                className="filter-pill px-4 py-2 rounded-xl text-xs sm:text-sm"
                style={{
                  background: activeCategory === f.key ? accent : bgCard,
                  color: activeCategory === f.key ? (isDark ? '#100718' : '#FFFFFF') : textSec,
                  borderColor: activeCategory === f.key ? accent : border,
                }}
              >
                {f.label} ({count})
              </button>
            );
          })}
        </div>

        {/* Projects Grid */}
        {filteredProjects.length > 0 ? (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {filteredProjects.map(project => (
              <ProjectCard key={project.id} project={project} onOpenDetails={onOpenDetails} />
            ))}
          </div>
        ) : (
          <div
            className="p-12 text-center rounded-2xl"
            style={{ background: bgCard, border: `1px solid ${border}` }}
          >
            <i className="fa-solid fa-magnifying-glass text-3xl mb-3" style={{ color: textSec }}></i>
            <h3 className="text-lg font-bold" style={{ color: textMain }}>No projects found</h3>
            <p className="text-sm mt-1" style={{ color: textSec }}>
              Try adjusting your search or switching categories.
            </p>
          </div>
        )}

      </div>
    </section>
  );
}
