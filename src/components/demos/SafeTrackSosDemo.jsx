import React, { useState } from 'react';
import { useTheme } from '../../context/ThemeContext';

export default function SafeTrackSosDemo() {
  const { isDark } = useTheme();
  const accent = isDark ? '#D4AF37' : '#16834A';
  const bgCard = isDark ? '#1a0a28' : '#0f1a14';
  const border = isDark ? 'rgba(212,175,55,0.3)' : 'rgba(32,165,107,0.4)';

  const [isSosTriggered, setIsSosTriggered] = useState(false);

  const triggerSos = () => {
    setIsSosTriggered(true);
  };

  const resetSos = () => {
    setIsSosTriggered(false);
  };

  return (
    <div
      className="rounded-2xl p-6 overflow-hidden transition-all duration-300"
      style={{ background: bgCard, border: `1px solid ${border}` }}
    >
      <div className="flex items-center justify-between pb-4 border-b border-slate-700/60">
        <div className="flex items-center gap-3">
          <div className="w-10 h-10 rounded-xl bg-rose-500/20 flex items-center justify-center text-rose-300">
            <i className="fa-solid fa-tower-broadcast"></i>
          </div>
          <div>
            <h3 className="font-bold text-white text-base">SafeTrack SOS Emergency Uplink</h3>
            <p className="text-xs text-rose-300 font-mono">ESP32 + NEO-6M GPS + SIM800L</p>
          </div>
        </div>
        {isSosTriggered ? (
          <span className="px-2.5 py-1 rounded-full text-xs font-mono font-semibold bg-rose-500/20 text-rose-300 border border-rose-500/40 animate-pulse">
            EMERGENCY ACTIVE
          </span>
        ) : (
          <span className="px-2.5 py-1 rounded-full text-xs font-mono font-semibold bg-emerald-500/10 text-emerald-400 border border-emerald-500/30">
            STANDBY
          </span>
        )}
      </div>

      <div className="mt-4 space-y-4">
        <div className="bg-black/60 rounded-xl p-3 font-mono text-xs text-slate-300 space-y-1.5 border border-slate-800 min-h-[95px]">
          <div className="text-slate-500">// Hardware: ESP32-WROOM-32 @ 240MHz</div>
          <div className="text-slate-500">// GPS Fix: Lat: 11.1085° N, Lon: 77.3411° E (Tirupur)</div>
          {isSosTriggered ? (
            <div className="space-y-1">
              <div className="text-rose-400 font-bold">&gt; [SOS TRIGGERED] Button pressed (Debounce 200ms verified)</div>
              <div className="text-cyan-300">&gt; GPS Fix: Lat: 11.1085° N, Lon: 77.3411° E | Alt: 310m</div>
              <div className="text-amber-300">&gt; SIM800L AT Command: AT+CMGS="+917639294309" (SMS Dispatched)</div>
              <div className="text-emerald-400 font-bold">&gt; Emergency link broadcasted to police dispatch portal: maps.google.com/?q=11.1085,77.3411</div>
            </div>
          ) : (
            <div className="text-slate-300">&gt; GPS Lock: 8 Satellites tracked. Ready for SOS trigger.</div>
          )}
        </div>

        <div className="flex items-center justify-between gap-4 p-3 rounded-xl bg-slate-900 border border-slate-800 text-xs">
          <div>
            <span className="text-slate-400 block font-mono">Cellular Uplink</span>
            <span className={isSosTriggered ? "text-rose-400 font-bold animate-pulse" : "text-white font-bold"}>
              {isSosTriggered ? "TRANSMITTING ALERTS..." : "SIM800L Connected (4G Failover)"}
            </span>
          </div>
          <div>
            <span className="text-slate-400 block font-mono">Battery</span>
            <span className="text-emerald-400 font-bold">3.7V Li-Po (98%)</span>
          </div>
        </div>

        <div className="flex flex-wrap gap-2 pt-2">
          <button
            onClick={triggerSos}
            className="px-4 py-2 rounded-lg bg-rose-500 hover:bg-rose-400 text-white font-bold text-xs transition shadow-lg"
          >
            <i className="fa-solid fa-bell mr-1"></i> Trigger Hardware SOS
          </button>
          <button
            onClick={resetSos}
            className="px-3 py-2 rounded-lg bg-slate-800 hover:bg-slate-700 text-slate-300 text-xs font-semibold transition"
          >
            Reset Device
          </button>
        </div>
      </div>
    </div>
  );
}

