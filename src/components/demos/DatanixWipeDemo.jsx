import React, { useState, useEffect, useRef } from 'react';
import { useTheme } from '../../context/ThemeContext';

export default function DatanixWipeDemo() {
  const { isDark } = useTheme();
  const accent = isDark ? '#D4AF37' : '#16834A';
  const bgCard = isDark ? '#1a0a28' : '#0f1a14';
  const border = isDark ? 'rgba(212,175,55,0.3)' : 'rgba(32,165,107,0.4)';

  const [progress, setProgress] = useState(0);
  const [isWiping, setIsWiping] = useState(false);
  const [isComplete, setIsComplete] = useState(false);
  const intervalRef = useRef(null);

  const startWipe = () => {
    if (intervalRef.current) clearInterval(intervalRef.current);
    setIsWiping(true);
    setIsComplete(false);
    setProgress(0);

    intervalRef.current = setInterval(() => {
      setProgress((prev) => {
        if (prev >= 100) {
          clearInterval(intervalRef.current);
          setIsWiping(false);
          setIsComplete(true);
          return 100;
        }
        return prev + 4;
      });
    }, 70);
  };

  const resetWipe = () => {
    if (intervalRef.current) clearInterval(intervalRef.current);
    setIsWiping(false);
    setIsComplete(false);
    setProgress(0);
  };

  useEffect(() => {
    return () => {
      if (intervalRef.current) clearInterval(intervalRef.current);
    };
  }, []);

  const getPassInfo = () => {
    if (isComplete) return "Verification Complete";
    if (progress < 33) return "Pass 1/3 (Zero Fill 0x00)";
    if (progress < 66) return "Pass 2/3 (Complement 0xFF)";
    return "Pass 3/3 (Random 0xRND)";
  };

  const getHexText = () => {
    if (isComplete) {
      return (
        <span className="text-emerald-400">
          &gt; [SUCCESS] Drive /dev/sdb successfully sanitized per DoD 5220.22-M & NIST SP 800-88. SHA-256 Bitwise audit confirmed zero recovery.
        </span>
      );
    }
    if (!isWiping && progress === 0) {
      return "[SECTOR 0x00000000] 00 00 00 00 00 00 00 00 | READY TO SANITIZE";
    }
    const sectorHex = (progress * 1024).toString(16).padStart(8, '0');
    if (progress < 33) {
      return `[SECTOR 0x${sectorHex}] 00 00 00 00 00 00 00 00 | DoD PASS 1 ZERO-FILL`;
    } else if (progress < 66) {
      return `[SECTOR 0x${sectorHex}] FF FF FF FF FF FF FF FF | DoD PASS 2 COMPLEMENT`;
    } else {
      return `[SECTOR 0x${sectorHex}] A3 9F 12 D8 4C 7E B0 05 | DoD PASS 3 CRYPTO NOISE`;
    }
  };

  return (
    <div
      className="rounded-2xl p-6 overflow-hidden transition-all duration-300"
      style={{ background: bgCard, border: `1px solid ${border}` }}
    >
      <div className="flex items-center justify-between pb-4 border-b border-slate-700/60">
        <div className="flex items-center gap-3">
          <div className="w-10 h-10 rounded-xl bg-cyan-500/20 flex items-center justify-center text-cyan-300">
            <i className="fa-solid fa-hard-drive"></i>
          </div>
          <div>
            <h3 className="font-bold text-white text-base">Datanix Secure Disk Sanitizer</h3>
            <p className="text-xs text-cyan-300 font-mono">DoD 5220.22-M & NIST SP 800-88</p>
          </div>
        </div>
        {isComplete ? (
          <span className="px-2.5 py-1 rounded-full text-xs font-mono font-semibold bg-emerald-500/20 text-emerald-300 border border-emerald-500/40">
            SANITIZED (100% UNRECOVERABLE)
          </span>
        ) : isWiping ? (
          <span className="px-2.5 py-1 rounded-full text-xs font-mono font-semibold bg-amber-500/20 text-amber-300 border border-amber-500/40 animate-pulse">
            WIPING IN PROGRESS
          </span>
        ) : (
          <span className="px-2.5 py-1 rounded-full text-xs font-mono font-semibold bg-cyan-500/10 text-cyan-400 border border-cyan-500/30">
            READY
          </span>
        )}
      </div>

      <div className="mt-4 space-y-4">
        <div className="space-y-2">
          <div className="flex justify-between text-xs font-mono text-slate-400">
            <span>Sanitization Pass Progress ({getPassInfo()})</span>
            <span className="text-cyan-400 font-bold">{progress}%</span>
          </div>
          <div className="w-full bg-slate-800 rounded-full h-3 overflow-hidden border border-slate-700">
            <div
              className="bg-gradient-to-r from-cyan-400 to-teal-400 h-full transition-all duration-300"
              style={{ width: `${progress}%` }}
            ></div>
          </div>
        </div>

        {/* Hex stream simulation */}
        <div className="bg-black/60 rounded-xl p-3 font-mono text-[11px] text-cyan-400/80 h-20 overflow-hidden border border-slate-800 flex items-center">
          <div>{getHexText()}</div>
        </div>

        <div className="flex flex-wrap gap-2 pt-2">
          <button
            onClick={startWipe}
            disabled={isWiping}
            className="px-4 py-2 rounded-lg bg-cyan-400 hover:bg-cyan-300 text-black font-bold text-xs transition glow-cyan disabled:opacity-50"
          >
            <i className="fa-solid fa-play mr-1.5"></i> Run DoD 3-Pass Wipe
          </button>
          <button
            onClick={resetWipe}
            className="px-3 py-2 rounded-lg bg-slate-800 hover:bg-slate-700 text-slate-300 text-xs font-semibold transition"
          >
            Reset Drive
          </button>
        </div>
      </div>
    </div>
  );
}

