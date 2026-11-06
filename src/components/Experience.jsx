import React from 'react';
import { useTheme } from '../context/ThemeContext';
import { experienceData } from '../data/portfolioData';

export default function Experience() {
  const { isDark } = useTheme();

  const accent   = isDark ? '#D4AF37' : '#16834A';
  const bgCard   = isDark ? '#241132' : '#FFFFFF';
  const bgSec    = isDark ? '#1B0B29' : '#E7F3E9';
  const border   = isDark ? 'rgba(212,175,55,0.2)' : 'rgba(22,131,74,0.2)';
  const textMain = isDark ? '#FFFFFF' : '#101B14';
  const textSec  = isDark ? '#C9BFD1' : '#53665A';

  const badgeColors = [
    { bg: isDark ? 'rgba(212,175,55,0.15)' : 'rgba(22,131,74,0.1)', text: accent, border: `${accent}40` },
    { bg: isDark ? 'rgba(84,36,119,0.3)'   : 'rgba(22,131,74,0.08)', text: isDark ? '#D4AF37' : '#16834A', border: `${accent}35` },
    { bg: isDark ? 'rgba(14,116,144,0.2)'  : 'rgba(32,165,107,0.1)', text: isDark ? '#67e8f9' : '#059669', border: isDark ? '#67e8f930' : '#05966930' },
  ];
  const lineColors = [accent, isDark ? '#a855f7' : '#20A56B', isDark ? '#67e8f9' : '#059669'];

  return (
    <section id="experience" className="py-12 sm:py-16 relative" style={{ borderTop: `1px solid ${accent}15`, background: isDark ? 'rgba(27,11,41,0.5)' : 'rgba(231,243,233,0.4)' }}>
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">

        <div className="max-w-2xl mb-8 sm:mb-12">
          <div className="section-label inline-flex items-center gap-2 mb-1.5 sm:mb-2 text-xs">
            <i className="fa-solid fa-briefcase"></i> Work History
          </div>
          <h2 className="section-title text-2xl sm:text-4xl font-extrabold">Internship Experience</h2>
          <p className="text-xs sm:text-sm mt-1" style={{ color: textSec }}>
            Three progressive tenures at Hackup Technology focusing on ethical hacking, IoT emergency systems, and web application penetration testing.
          </p>
        </div>

        {/* Timeline */}
        <div className="relative">
          {/* Vertical line */}
          <div
            className="absolute left-5 top-0 bottom-0 w-0.5 hidden sm:block"
            style={{ background: `linear-gradient(to bottom, ${accent}, transparent)`, opacity: 0.4 }}
          />

          <div className="space-y-4 sm:space-y-6 sm:pl-14">
            {experienceData.map((item, idx) => {
              const bc = badgeColors[idx % badgeColors.length];
              const lc = lineColors[idx % lineColors.length];

              return (
                <div key={idx} className="relative">
                  {/* Timeline dot */}
                  <div
                    className="absolute -left-[46px] top-6 w-5 h-5 rounded-full border-2 items-center justify-center hidden sm:flex"
                    style={{ background: isDark ? '#1B0B29' : '#E7F3E9', borderColor: lc, boxShadow: `0 0 10px ${lc}60` }}
                  >
                    <div className="w-2 h-2 rounded-full" style={{ background: lc }}></div>
                  </div>

                  <div
                    className="themed-card rounded-2xl p-4 sm:p-7 relative overflow-hidden"
                    style={{
                      background: bgCard,
                      border: `1px solid ${border}`,
                      borderLeft: `3px solid ${lc}`,
                    }}
                  >
                    {/* Background glow */}
                    <div
                      className="absolute -top-16 -right-16 w-40 h-40 rounded-full blur-3xl pointer-events-none"
                      style={{ background: `${lc}08` }}
                    />

                    <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-2.5 mb-3.5 sm:mb-4">
                      <div>
                        <span
                          className="px-2.5 py-0.5 sm:px-3 sm:py-1 rounded-full text-[11px] sm:text-xs font-mono font-bold inline-block"
                          style={{ background: bc.bg, color: bc.text, border: `1px solid ${bc.border}` }}
                        >
                          {item.badge}
                        </span>
                        <h3
                          className="text-lg sm:text-2xl font-bold mt-1.5 sm:mt-2"
                          style={{ fontFamily: 'Space Grotesk, sans-serif', color: textMain }}
                        >
                          {item.company}
                        </h3>
                        <p className="text-xs sm:text-sm mt-0.5" style={{ color: textSec }}>
                          {item.role} &bull; {item.location}
                        </p>
                      </div>
                      <span
                        className="self-start sm:self-auto shrink-0 px-2.5 py-1 sm:px-3 sm:py-1.5 rounded-lg text-[11px] sm:text-xs font-mono"
                        style={{
                          background: isDark ? '#100718' : '#F5FAF6',
                          color: accent,
                          border: `1px solid ${accent}30`,
                        }}
                      >
                        {item.period}
                      </span>
                    </div>

                    <p className="text-xs sm:text-sm leading-relaxed mb-4 sm:mb-5" style={{ color: textSec }}>
                      {item.desc}
                    </p>

                    <div className="grid grid-cols-1 md:grid-cols-2 gap-2 sm:gap-3 text-xs sm:text-sm mb-4 sm:mb-5">
                      {item.bullets.map((bullet, bIdx) => (
                        <div key={bIdx} className="flex items-start gap-2">
                          <i className="fa-solid fa-circle-check mt-1 text-xs shrink-0" style={{ color: accent }}></i>
                          <span style={{ color: textSec }}>{bullet}</span>
                        </div>
                      ))}
                    </div>

                    <div
                      className="pt-3 sm:pt-4 flex flex-wrap items-center gap-1.5 sm:gap-2"
                      style={{ borderTop: `1px solid ${border}` }}
                    >
                      <span className="text-[11px] sm:text-xs font-medium mr-1" style={{ color: textSec }}>Tools:</span>
                      {item.tools.map((tool, tIdx) => (
                        <span
                          key={tIdx}
                          className="skill-badge px-2.5 py-1 rounded text-xs"
                        >
                          {tool}
                        </span>
                      ))}
                    </div>
                  </div>
                </div>
              );
            })}
          </div>
        </div>

      </div>
    </section>
  );
}
