import React from 'react';
import { useTheme } from '../context/ThemeContext';
import { educationData, certificationsData, awardsData } from '../data/portfolioData';

export default function AcademicsAwards() {
  const { isDark } = useTheme();

  const accent   = isDark ? '#D4AF37' : '#16834A';
  const bgCard   = isDark ? '#241132' : '#FFFFFF';
  const bgStat   = isDark ? 'rgba(84,36,119,0.2)' : 'rgba(22,131,74,0.08)';
  const border   = isDark ? 'rgba(212,175,55,0.2)' : 'rgba(22,131,74,0.2)';
  const textMain = isDark ? '#FFFFFF' : '#101B14';
  const textSec  = isDark ? '#C9BFD1' : '#53665A';

  const awardIconColor = (color) => {
    if (color === 'amber') return isDark ? { bg: 'rgba(245,158,11,0.15)', text: '#fbbf24', border: '#fbbf2430' } : { bg: 'rgba(245,158,11,0.1)', text: '#d97706', border: '#d9770630' };
    if (color === 'pink')  return isDark ? { bg: 'rgba(236,72,153,0.15)', text: '#f9a8d4', border: '#f9a8d430' } : { bg: 'rgba(236,72,153,0.08)', text: '#db2777', border: '#db277730' };
    if (color === 'purple') return isDark ? { bg: 'rgba(84,36,119,0.3)', text: '#c084fc', border: '#c084fc30' } : { bg: 'rgba(22,131,74,0.08)', text: '#16834A', border: '#16834A30' };
    return { bg: bgStat, text: accent, border: `${accent}30` };
  };

  return (
    <section id="awards" className="py-12 sm:py-16 relative" style={{ borderTop: `1px solid ${accent}15`, background: isDark ? 'rgba(27,11,41,0.5)' : 'rgba(231,243,233,0.4)' }}>
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">

        <div className="section-label inline-flex items-center gap-2 mb-2">
          <i className="fa-solid fa-graduation-cap"></i> Academics &amp; Honors
        </div>
        <h2 className="section-title text-2xl sm:text-4xl font-extrabold mb-6 sm:mb-10">
          Education, Certifications &amp; Awards
        </h2>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 sm:gap-12">

          {/* ════ LEFT: Education + Certifications ════ */}
          <div className="space-y-8">

            <div>
              <h3
                className="text-lg font-bold mb-4 flex items-center gap-2"
                style={{ fontFamily: 'Space Grotesk, sans-serif', color: textMain }}
              >
                <i className="fa-solid fa-school" style={{ color: accent }}></i>
                Education Background
              </h3>
              <div className="space-y-4">
                {educationData.map((edu, idx) => (
                  <div
                    key={idx}
                    className="themed-card rounded-2xl p-5 relative overflow-hidden"
                    style={{
                      background: bgCard,
                      border: `1px solid ${border}`,
                      borderLeft: idx === 0 ? `3px solid ${accent}` : `3px solid ${accent}50`,
                    }}
                  >
                    <div
                      className="absolute -top-10 -right-10 w-28 h-28 rounded-full blur-2xl pointer-events-none"
                      style={{ background: idx === 0 ? `${accent}10` : 'transparent' }}
                    />
                    <div className="flex items-start justify-between gap-3">
                      <div>
                        <h4
                          className="text-base font-bold"
                          style={{ fontFamily: 'Space Grotesk, sans-serif', color: textMain }}
                        >
                          {edu.degree}
                        </h4>
                        <p className="text-sm font-medium mt-0.5" style={{ color: accent }}>
                          {edu.institution}
                        </p>
                        <p className="text-xs mt-0.5" style={{ color: textSec }}>
                          {edu.location} &bull; {edu.period}
                        </p>
                      </div>
                      <span
                        className="shrink-0 px-3 py-1 rounded-full text-xs font-black"
                        style={{
                          background: idx === 0 ? accent : bgStat,
                          color: idx === 0 ? (isDark ? '#100718' : '#FFFFFF') : accent,
                        }}
                      >
                        {edu.score}
                      </span>
                    </div>
                    {edu.highlight && (
                      <div
                        className="mt-3 pt-3 text-xs"
                        style={{ borderTop: `1px solid ${border}`, color: textSec }}
                      >
                        {edu.highlight}
                      </div>
                    )}
                  </div>
                ))}
              </div>
            </div>

            {/* Certifications */}
            <div id="certifications" className="scroll-mt-24">
              <h3
                className="text-lg font-bold mb-4 flex items-center gap-2"
                style={{ fontFamily: 'Space Grotesk, sans-serif', color: textMain }}
              >
                <i className="fa-solid fa-stamp" style={{ color: accent }}></i>
                Certifications &amp; Diplomas
              </h3>
              <div className="space-y-3">
                {certificationsData.map((cert, idx) => (
                  <div
                    key={idx}
                    className="themed-card rounded-xl p-4 flex items-center justify-between gap-3"
                    style={{ background: bgCard, border: `1px solid ${border}` }}
                  >
                    <div className="flex items-center gap-3">
                      <div
                        className="w-9 h-9 rounded-lg flex items-center justify-center shrink-0"
                        style={{ background: bgStat, color: accent }}
                      >
                        <i className="fa-solid fa-certificate text-sm"></i>
                      </div>
                      <div>
                        <div className="text-sm font-semibold" style={{ color: textMain }}>{cert.title}</div>
                        <div className="text-xs mt-0.5" style={{ color: accent, fontFamily: 'JetBrains Mono, monospace' }}>
                          {cert.issuer}{cert.code ? ` • ${cert.code}` : ''}
                        </div>
                      </div>
                    </div>
                    <span className="text-xs font-mono shrink-0" style={{ color: textSec }}>{cert.date}</span>
                  </div>
                ))}
              </div>
            </div>
          </div>

          {/* ════ RIGHT: Awards ════ */}
          <div>
            <h3
              className="text-lg font-bold mb-4 flex items-center gap-2"
              style={{ fontFamily: 'Space Grotesk, sans-serif', color: textMain }}
            >
              <i className="fa-solid fa-trophy" style={{ color: accent }}></i>
              Awards &amp; Competitions
            </h3>
            <div className="space-y-4">
              {awardsData.map((award, idx) => {
                const ac = awardIconColor(award.color);
                return (
                  <div
                    key={idx}
                    className="themed-card rounded-xl p-4 flex items-start sm:items-center justify-between gap-4 flex-wrap sm:flex-nowrap"
                    style={{ background: bgCard, border: `1px solid ${border}` }}
                  >
                    <div className="flex items-center gap-4">
                      <div
                        className="w-11 h-11 rounded-xl flex items-center justify-center text-xl shrink-0"
                        style={{ background: ac.bg, color: ac.text, border: `1px solid ${ac.border}` }}
                      >
                        <i className={`fa-solid ${award.icon}`}></i>
                      </div>
                      <div>
                        <div className="text-sm font-bold" style={{ color: textMain }}>{award.title}</div>
                        <div className="text-xs mt-0.5" style={{ color: textSec }}>
                          {award.organizer} &bull; {award.date}
                        </div>
                      </div>
                    </div>
                    {award.badge && (
                      <span
                        className="inline-flex px-2.5 py-1 rounded-full text-[11px] font-mono font-semibold shrink-0"
                        style={{ background: ac.bg, color: ac.text, border: `1px solid ${ac.border}` }}
                      >
                        {award.badge}
                      </span>
                    )}
                  </div>
                );
              })}
            </div>
          </div>

        </div>
      </div>
    </section>
  );
}
