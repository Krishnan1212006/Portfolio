import React from 'react';
import { useTheme } from '../context/ThemeContext';
import CyberThreatDemo from './demos/CyberThreatDemo';
import DatanixWipeDemo from './demos/DatanixWipeDemo';
import SafeTrackSosDemo from './demos/SafeTrackSosDemo';
import SatlinkTelemetryDemo from './demos/SatlinkTelemetryDemo';

export default function LiveDemos() {
  const { isDark } = useTheme();

  const accent   = isDark ? '#D4AF37' : '#16834A';
  const textSec  = isDark ? '#C9BFD1' : '#53665A';
  const textMain = isDark ? '#FFFFFF' : '#101B14';

  return (
    <section
      id="demos"
      className="py-16 relative"
      style={{
        borderTop: `1px solid ${accent}15`,
        background: isDark ? 'rgba(27,11,41,0.5)' : 'rgba(231,243,233,0.4)',
      }}
    >
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">

        <div className="text-center max-w-3xl mx-auto mb-12">
          <div className="section-label inline-flex items-center gap-2 mb-2">
            <i className="fa-solid fa-terminal"></i> In-Browser Simulation
          </div>
          <h2
            className="section-title text-3xl sm:text-4xl font-extrabold"
          >
            Live Interactive Telemetry Demos
          </h2>
          <p className="text-sm sm:text-base mt-2" style={{ color: textSec }}>
            Experience real-time threat modeling, cryptographic disk sanitization, cellular GPS telemetry, and satellite ground links directly in your browser.
          </p>
          <p
            className="text-xs mt-3 font-mono"
            style={{
              color: isDark ? '#D4AF37' : '#16834A',
              background: isDark ? 'rgba(212,175,55,0.08)' : 'rgba(22,131,74,0.08)',
              border: `1px solid ${accent}30`,
              display: 'inline-block',
              padding: '0.3rem 0.75rem',
              borderRadius: '0.5rem',
            }}
          >
            ⚠ Client-side simulators — not connected to real networks
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
          <CyberThreatDemo />
          <DatanixWipeDemo />
          <SafeTrackSosDemo />
          <SatlinkTelemetryDemo />
        </div>

      </div>
    </section>
  );
}
