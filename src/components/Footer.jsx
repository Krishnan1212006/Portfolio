import React, { useState, useEffect } from 'react';
import { useTheme } from '../context/ThemeContext';
import { profileData } from '../data/portfolioData';

export default function Footer() {
  const { isDark } = useTheme();
  const [showTop, setShowTop] = useState(false);

  const accent   = isDark ? '#D4AF37' : '#16834A';
  const textSec  = isDark ? '#C9BFD1' : '#53665A';
  const textMain = isDark ? '#FFFFFF' : '#101B14';

  useEffect(() => {
    const onScroll = () => setShowTop(window.scrollY > 400);
    window.addEventListener('scroll', onScroll, { passive: true });
    return () => window.removeEventListener('scroll', onScroll);
  }, []);

  const scrollToTop = () => window.scrollTo({ top: 0, behavior: 'smooth' });

  const links = [
    { label: 'About',              href: '#about'          },
    { label: 'Projects',           href: '#projects'       },
    { label: 'Experience',         href: '#experience'     },
    { label: 'Skills',             href: '#skills'         },
    { label: 'Certifications',     href: '#certifications' },
    { label: 'Academics & Awards', href: '#awards'         },
    { label: 'Contact',            href: '#contact'        },
  ];

  return (
    <>
      <footer
        className="footer-bg py-10"
        style={{ background: isDark ? '#1B0B29' : '#E7F3E9', borderTop: `1px solid ${accent}20` }}
      >
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8 mb-8">

            {/* Brand */}
            <div>
              <h3
                className="font-bold text-lg tracking-widest mb-1"
                style={{ fontFamily: 'Space Grotesk, sans-serif', color: textMain }}
              >
                HARI KRISHNAN.S
              </h3>
              <p className="text-xs font-mono mb-3" style={{ color: accent }}>
                Cybersecurity &amp; Embedded IoT
              </p>
              <p className="text-xs leading-relaxed" style={{ color: textSec }}>
                Computer Science Engineer passionate about building secure systems, IoT solutions, and AI-powered technologies.
              </p>
            </div>

            {/* Quick Nav */}
            <div>
              <h4 className="text-sm font-bold mb-3" style={{ color: textMain, fontFamily: 'Space Grotesk, sans-serif' }}>
                Quick Navigation
              </h4>
              <ul className="space-y-1.5">
                {links.map(link => (
                  <li key={link.href}>
                    <a
                      href={link.href}
                      className="text-xs transition-colors"
                      style={{ color: textSec }}
                      onMouseEnter={e => e.currentTarget.style.color = accent}
                      onMouseLeave={e => e.currentTarget.style.color = textSec}
                    >
                      {link.label}
                    </a>
                  </li>
                ))}
              </ul>
            </div>

            {/* Contact */}
            <div>
              <h4 className="text-sm font-bold mb-3" style={{ color: textMain, fontFamily: 'Space Grotesk, sans-serif' }}>
                Connect
              </h4>
              <div className="space-y-2">
                <a
                  href={`mailto:${profileData.email}`}
                  className="flex items-center gap-2 text-xs transition-colors"
                  style={{ color: textSec, fontFamily: 'JetBrains Mono, monospace' }}
                  onMouseEnter={e => e.currentTarget.style.color = accent}
                  onMouseLeave={e => e.currentTarget.style.color = textSec}
                >
                  <i className="fa-solid fa-envelope" style={{ color: accent }}></i>
                  {profileData.email}
                </a>
                <a
                  href={profileData.github}
                  target="_blank"
                  rel="noopener noreferrer"
                  className="flex items-center gap-2 text-xs transition-colors"
                  style={{ color: textSec, fontFamily: 'JetBrains Mono, monospace' }}
                  onMouseEnter={e => e.currentTarget.style.color = accent}
                  onMouseLeave={e => e.currentTarget.style.color = textSec}
                >
                  <i className="fa-brands fa-github" style={{ color: textMain }}></i>
                  github.com/{profileData.githubUsername}
                </a>
                <a
                  href={profileData.linkedin}
                  target="_blank"
                  rel="noopener noreferrer"
                  className="flex items-center gap-2 text-xs transition-colors"
                  style={{ color: textSec }}
                  onMouseEnter={e => e.currentTarget.style.color = accent}
                  onMouseLeave={e => e.currentTarget.style.color = textSec}
                >
                  <i className="fa-brands fa-linkedin" style={{ color: accent }}></i>
                  LinkedIn Profile
                </a>
              </div>
            </div>
          </div>

          {/* Bottom bar */}
          <div
            className="pt-6 flex flex-col sm:flex-row items-center justify-between gap-3 text-xs"
            style={{ borderTop: `1px solid ${accent}15`, color: textSec }}
          >
            <span style={{ fontFamily: 'JetBrains Mono, monospace' }}>
              &copy; {new Date().getFullYear()} Hari Krishnan.S — All Rights Reserved
            </span>
            <span>
              Built with React &amp; Vite &bull; Tirupur, Tamil Nadu, India
            </span>
          </div>
        </div>
      </footer>

      {/* Back to Top */}
      {showTop && (
        <button
          onClick={scrollToTop}
          className="back-to-top"
          aria-label="Back to top"
          title="Back to top"
        >
          <i className="fa-solid fa-arrow-up text-sm"></i>
        </button>
      )}
    </>
  );
}
