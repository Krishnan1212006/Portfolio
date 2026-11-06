import React, { useEffect } from 'react';
import { useTheme } from '../context/ThemeContext';

export default function ProfileImageModal({ onClose }) {
  const { isDark } = useTheme();

  // Lock background scroll
  useEffect(() => {
    document.body.style.overflow = 'hidden';
    return () => { document.body.style.overflow = ''; };
  }, []);

  // ESC key handler
  useEffect(() => {
    const handleKey = (e) => { if (e.key === 'Escape') onClose(); };
    window.addEventListener('keydown', handleKey);
    return () => window.removeEventListener('keydown', handleKey);
  }, [onClose]);

  const borderColor = isDark ? '#D4AF37' : '#16834A';
  const bgColor     = isDark ? '#1B0B29'  : '#FFFFFF';
  const titleColor  = isDark ? '#FFFFFF'  : '#101B14';
  const subColor    = isDark ? '#C9BFD1'  : '#53665A';
  const closeColor  = isDark ? '#D4AF37'  : '#16834A';

  return (
    <div
      className="modal-backdrop"
      onClick={onClose}
      role="dialog"
      aria-modal="true"
      aria-label="HD Profile Photo"
    >
      <div
        className="modal-content relative max-w-sm sm:max-w-md w-[92vw] mx-auto rounded-2xl overflow-hidden shadow-2xl"
        style={{ background: bgColor, border: `2px solid ${borderColor}` }}
        onClick={e => e.stopPropagation()}
      >
        {/* Close button */}
        <button
          onClick={onClose}
          aria-label="Close modal"
          className="absolute top-2.5 right-2.5 z-20 w-9 h-9 rounded-full flex items-center justify-center transition hover:scale-110 shadow-lg"
          style={{ background: 'rgba(0,0,0,0.5)', color: '#FFFFFF', backdropFilter: 'blur(6px)' }}
        >
          <i className="fa-solid fa-xmark text-base"></i>
        </button>

        {/* Image */}
        <div className="w-full flex items-center justify-center p-2.5 sm:p-3 pt-3 sm:pt-4" style={{ maxHeight: '68vh' }}>
          <img
            src="./images/hari-profile.jpg"
            alt="HD Profile Photo of Hari Krishnan.S"
            className="w-full h-full object-contain rounded-xl shadow-md"
            style={{ maxHeight: '64vh', display: 'block' }}
            loading="eager"
            onError={(e) => {
              if (!e.target.src.includes('avatar.jpg')) {
                e.target.src = './assets/images/avatar.jpg';
              }
            }}
          />
        </div>

        {/* Title bar */}
        <div
          className="px-4 py-3 sm:px-6 sm:py-3.5 text-center"
          style={{ borderTop: `1px solid ${borderColor}30`, background: bgColor }}
        >
          <h3
            className="text-xs sm:text-sm font-bold tracking-wider sm:tracking-widest leading-tight"
            style={{ fontFamily: 'JetBrains Mono, monospace', color: borderColor }}
          >
            HD PROFILE PHOTO • HARI KRISHNAN.S
          </h3>
          <p className="text-[11px] sm:text-xs mt-0.5" style={{ color: subColor }}>
            Cybersecurity &amp; Embedded IoT
          </p>
        </div>
      </div>
    </div>
  );
}

