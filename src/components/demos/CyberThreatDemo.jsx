import React, { useState } from 'react';
import { useTheme } from '../../context/ThemeContext';

export default function CyberThreatDemo() {
  const { isDark } = useTheme();
  const accent = isDark ? '#D4AF37' : '#16834A';
  const bgCard = isDark ? '#1a0a28' : '#0f1a14';
  const border = isDark ? 'rgba(212,175,55,0.3)' : 'rgba(32,165,107,0.4)';
  const [attackType, setAttackType] = useState(null);

  const simulateAttack = (type) => {
    setAttackType(type);
  };

  const resetAttack = () => {
    setAttackType(null);
  };

  return (
    <div
      className="rounded-2xl p-6 overflow-hidden"
      style={{ background: bgCard, border: `1px solid ${border}` }}
    >
      <div className="flex items-center justify-between pb-4 border-b border-slate-700/60">
        <div className="flex items-center gap-3">
          <div className="w-10 h-10 rounded-xl bg-purple-500/20 flex items-center justify-center text-purple-300">
            <i className="fa-solid fa-network-wired"></i>
          </div>
          <div>
            <h3 className="font-bold text-white text-base">CyberResilience AI Threat Telemetry</h3>
            <p className="text-xs text-purple-300 font-mono">MITRE ATT&CK Model Simulator</p>
          </div>
        </div>
        {attackType === 'syn_flood' && (
          <span className="px-2.5 py-1 rounded-full text-xs font-mono font-semibold bg-rose-500/20 text-rose-300 border border-rose-500/40 animate-pulse">
            ATTACK DETECTED: SYN FLOOD (T1498)
          </span>
        )}
        {attackType === 'exfiltration' && (
          <span className="px-2.5 py-1 rounded-full text-xs font-mono font-semibold bg-amber-500/20 text-amber-300 border border-amber-500/40 animate-pulse">
            DATA EXFILTRATION SUSPECTED (T1048)
          </span>
        )}
        {!attackType && (
          <span className="px-2.5 py-1 rounded-full text-xs font-mono font-semibold bg-emerald-500/10 text-emerald-400 border border-emerald-500/30">
            IDLE / NORMAL
          </span>
        )}
      </div>

      <div className="mt-4 space-y-4">
        <div className="bg-black/60 rounded-xl p-4 font-mono text-xs text-slate-300 space-y-2 border border-slate-800 min-h-[110px]">
          <div className="text-slate-500">// AI Model Loaded: PyTorch LSTM/GRU Temporal Classifier</div>
          <div className="text-slate-500">// Ingress Interface: eth0 (Zeek + Wazuh + Suricata Telemetry)</div>

          {attackType === 'syn_flood' && (
            <div className="space-y-1">
              <div className="text-rose-400">&gt; [ALERT] 15,400 packets/sec flagged on TCP port 443!</div>
              <div className="text-amber-300">&gt; PyTorch Model classification: SYN Flood attack (Confidence: 99.4%)</div>
              <div className="text-cyan-300">&gt; Executing mitigation: iptables -A INPUT -s 198.51.100.42 -j DROP</div>
              <div className="text-emerald-400">&gt; Source quarantined. Host latency normalized to 2ms.</div>
            </div>
          )}

          {attackType === 'exfiltration' && (
            <div className="space-y-1">
              <div className="text-amber-400">&gt; [ANOMALY] Outbound UDP payload size &gt; 512 bytes on port 53.</div>
              <div className="text-purple-300">&gt; Shannon Entropy calculation: 7.92 (High encryption/obfuscation).</div>
              <div className="text-cyan-300">&gt; MITRE ATT&CK mapping: T1048.003 Protocol Tunneling.</div>
              <div className="text-emerald-400">&gt; Process PID 4092 terminated. Socket closed.</div>
            </div>
          )}

          {!attackType && (
            <div className="text-emerald-400">&gt; Monitoring packet stream... No anomalies detected.</div>
          )}
        </div>

        <div className="grid grid-cols-2 gap-3 text-xs">
          <div className="p-3 rounded-lg bg-slate-900 border border-slate-800">
            <span className="text-slate-500 block">Anomaly Score</span>
            <span className="text-lg font-bold text-white">
              {attackType === 'syn_flood' ? '0.98 (CRITICAL)' : attackType === 'exfiltration' ? '0.87 (HIGH)' : '0.02'}
            </span>
          </div>
          <div className="p-3 rounded-lg bg-slate-900 border border-slate-800">
            <span className="text-slate-500 block">Mitigation Status</span>
            <span className={`text-lg font-bold ${
              attackType === 'syn_flood' ? 'text-rose-400' : attackType === 'exfiltration' ? 'text-amber-400' : 'text-slate-400'
            }`}>
              {attackType === 'syn_flood' ? 'IPTABLES DROP ACTIVE' : attackType === 'exfiltration' ? 'DNS TUNNEL SEVERED' : 'Standby'}
            </span>
          </div>
        </div>

        {/* Action Buttons */}
        <div className="flex flex-wrap gap-2 pt-2">
          <button
            onClick={() => simulateAttack('syn_flood')}
            className="px-3 py-2 rounded-lg bg-rose-600/20 hover:bg-rose-600/30 text-rose-300 border border-rose-500/40 text-xs font-semibold transition"
          >
            <i className="fa-solid fa-bug mr-1"></i> Inject SYN Flood (T1498)
          </button>
          <button
            onClick={() => simulateAttack('exfiltration')}
            className="px-3 py-2 rounded-lg bg-amber-600/20 hover:bg-amber-600/30 text-amber-300 border border-amber-500/40 text-xs font-semibold transition"
          >
            <i className="fa-solid fa-user-secret mr-1"></i> Data Exfiltration (T1048)
          </button>
          <button
            onClick={resetAttack}
            className="px-3 py-2 rounded-lg bg-slate-800 hover:bg-slate-700 text-slate-300 text-xs font-semibold transition"
          >
            <i className="fa-solid fa-rotate-right mr-1"></i> Reset Feed
          </button>
        </div>
      </div>
    </div>
  );
}

