import React, { useState } from 'react';
import { useTheme } from '../../context/ThemeContext';

export default function SatlinkTelemetryDemo() {
  const { isDark } = useTheme();
  const accent = isDark ? '#D4AF37' : '#16834A';
  const bgCard = isDark ? '#1a0a28' : '#0f1a14';
  const border = isDark ? 'rgba(212,175,55,0.3)' : 'rgba(32,165,107,0.4)';

  const [isLocked, setIsLocked] = useState(false);

  const decodePacket = () => {
    setIsLocked(true);
  };

  const resetDownlink = () => {
    setIsLocked(false);
  };

  return (
    <div
      className="rounded-2xl p-6 overflow-hidden transition-all duration-300"
      style={{ background: bgCard, border: `1px solid ${border}` }}
    >
      <div className="flex items-center justify-between pb-4 border-b border-slate-700/60">
        <div className="flex items-center gap-3">
          <div className="w-10 h-10 rounded-xl bg-sky-500/20 flex items-center justify-center text-sky-300">
            <i className="fa-solid fa-satellite"></i>
          </div>
          <div>
            <h3 className="font-bold text-white text-base">SATLINK Orbital Telemetry Decoder</h3>
            <p className="text-xs text-sky-300 font-mono">ESP32-S3 + LoRa RF + AES-256</p>
          </div>
        </div>
        {isLocked ? (
          <span className="px-2.5 py-1 rounded-full text-xs font-mono font-semibold bg-sky-500/20 text-sky-300 border border-sky-500/40 animate-pulse">
            PACKET LOCKED
          </span>
        ) : (
          <span className="px-2.5 py-1 rounded-full text-xs font-mono font-semibold bg-sky-500/10 text-sky-400 border border-sky-500/30">
            ORBIT TRACKING
          </span>
        )}
      </div>

      <div className="mt-4 space-y-4">
        <div className="bg-black/60 rounded-xl p-3 font-mono text-xs text-slate-300 space-y-1.5 border border-slate-800 min-h-[95px]">
          <div className="text-slate-500">// LEO CubeSat Downlink Frequency: 437.525 MHz</div>
          <div className="text-slate-500">// Cryptographic Algorithm: AES-256-GCM Verification</div>
          {isLocked ? (
            <div className="space-y-1">
              <div className="text-sky-300">&gt; [UHF DOWNLINK] 437.525 MHz carrier locked (RSSI: -94 dBm, SNR: 8.2 dB)</div>
              <div className="text-purple-300">&gt; Raw Payload: 0x5341544C494E4B5F4F524249545F3432</div>
              <div className="text-cyan-300">&gt; Decrypting with AES-256-GCM hardware key... Auth Tag OK</div>
              <div className="text-emerald-400">&gt; Telemetry: CubeSat Temp: 18.4°C | Bus: 3.82V | Solar: 420mW | Orbit Pass: 87.2 min</div>
            </div>
          ) : (
            <div className="text-sky-300">&gt; Waiting for next orbital packet pass...</div>
          )}
        </div>

        <div className="grid grid-cols-2 gap-3 text-xs">
          <div className="p-3 rounded-lg bg-slate-900 border border-slate-800">
            <span className="text-slate-500 block">Doppler Shift</span>
            <span className="text-base font-bold text-cyan-300">
              {isLocked ? "+1.8 kHz (Tracking)" : "+2.4 kHz (Compensated)"}
            </span>
          </div>
          <div className="p-3 rounded-lg bg-slate-900 border border-slate-800">
            <span className="text-slate-500 block">Crypto Verification</span>
            <span className="text-base font-bold text-emerald-400">
              {isLocked ? "AES-256 Validated" : "Authenticated"}
            </span>
          </div>
        </div>

        <div className="flex flex-wrap gap-2 pt-2">
          <button
            onClick={decodePacket}
            className="px-4 py-2 rounded-lg bg-sky-500 hover:bg-sky-400 text-black font-bold text-xs transition"
          >
            <i className="fa-solid fa-satellite-dish mr-1"></i> Capture & Decode Orbit Packet
          </button>
          <button
            onClick={resetDownlink}
            className="px-3 py-2 rounded-lg bg-slate-800 hover:bg-slate-700 text-slate-300 text-xs font-semibold transition"
          >
            Reset Downlink
          </button>
        </div>
      </div>
    </div>
  );
}

