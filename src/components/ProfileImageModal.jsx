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
        className="modal-content relative max-w-md w-full mx-4 rounded-2xl overflow-hidden shadow-2xl"
        style={{ background: bgColor, border: `2px solid ${borderColor}` }}
        onClick={e => e.stopPropagation()}
      >
        {/* Close button */}
        <button
          onClick={onClose}
          aria-label="Close modal"
          className="absolute top-3 right-3 z-10 w-9 h-9 rounded-full flex items-center justify-center transition hover:scale-110"
          style={{ background: 'rgba(0,0,0,0.4)', color: '#FFFFFF', backdropFilter: 'blur(4px)' }}
        >
          <i className="fa-solid fa-xmark text-lg"></i>
        </button>

        {/* Image */}
        <div className="w-full flex items-center justify-center p-3 pt-4" style={{ maxHeight: '75vh' }}>
          <img
            src="./images/hari-profile.jpg"
            alt="HD Profile Photo of Hari Krishnan.S"
            className="w-full h-full object-contain rounded-xl shadow-lg"
            style={{ maxHeight: '70vh', display: 'block' }}
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
          className="px-6 py-4 text-center"
          style={{ borderTop: `1px solid ${borderColor}30`, background: bgColor }}
        >
          <h3
            className="text-base font-bold tracking-widest"
            style={{ fontFamily: 'JetBrains Mono, monospace', color: borderColor }}
          >
            HD PROFILE PHOTO • HARI KRISHNAN.S
          </h3>
          <p className="text-sm mt-0.5" style={{ color: subColor }}>
            Cybersecurity &amp; Embedded IoT
          </p>
        </div>
      </div>
    </div>
  );
}

