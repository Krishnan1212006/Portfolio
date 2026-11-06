import React, { useEffect, useRef } from 'react';
import { useTheme } from '../context/ThemeContext';

export default function CyberBackground() {
  const canvasRef = useRef(null);
  const { isDark } = useTheme();
  const animRef = useRef(null);

  useEffect(() => {
    // Check for reduced motion preference
    const prefersReduced = window.matchMedia('(prefers-reduced-motion: reduce)').matches;
    if (prefersReduced) return;

    const canvas = canvasRef.current;
    if (!canvas) return;
    const ctx = canvas.getContext('2d');

    // Colors based on theme
    const nodeColor   = isDark ? 'rgba(212, 175, 55, 0.7)'  : 'rgba(22, 131, 74, 0.7)';
    const lineColor   = isDark ? 'rgba(84, 36, 119, 0.35)'  : 'rgba(32, 165, 107, 0.3)';
    const binaryColor = isDark ? 'rgba(212, 175, 55, 0.18)' : 'rgba(22, 131, 74, 0.18)';

    let W = window.innerWidth;
    let H = window.innerHeight;
    canvas.width = W;
    canvas.height = H;

    // Particle count – fewer on mobile
    const isMobile = W < 768;
    const NODE_COUNT = isMobile ? 24 : 55;
    const BINARY_COUNT = isMobile ? 10 : 22;
    const CONNECT_DIST = isMobile ? 110 : 160;

    // Nodes
    const nodes = Array.from({ length: NODE_COUNT }, () => ({
      x: Math.random() * W,
      y: Math.random() * H,
      vx: (Math.random() - 0.5) * 0.35,
      vy: (Math.random() - 0.5) * 0.35,
      r: Math.random() * 2.5 + 1,
      pulse: Math.random() * Math.PI * 2,
    }));

    // Floating binary / hex chars
    const CHARS = ['0', '1', '0x', 'FF', '01', 'AE', 'SHA', 'IoT', '10', 'NET', '00'];
    const binaries = Array.from({ length: BINARY_COUNT }, () => ({
      x: Math.random() * W,
      y: Math.random() * H,
      vy: (Math.random() * 0.3 + 0.1) * (Math.random() > 0.5 ? 1 : -1),
      char: CHARS[Math.floor(Math.random() * CHARS.length)],
      opacity: Math.random() * 0.5 + 0.1,
      size: Math.floor(Math.random() * 5 + 8),
    }));

    function draw() {
      ctx.clearRect(0, 0, W, H);

      // Draw connecting lines
      for (let i = 0; i < nodes.length; i++) {
        for (let j = i + 1; j < nodes.length; j++) {
          const dx = nodes[i].x - nodes[j].x;
          const dy = nodes[i].y - nodes[j].y;
          const dist = Math.sqrt(dx * dx + dy * dy);
          if (dist < CONNECT_DIST) {
            const alpha = (1 - dist / CONNECT_DIST) * 0.6;
            ctx.beginPath();
            ctx.moveTo(nodes[i].x, nodes[i].y);
            ctx.lineTo(nodes[j].x, nodes[j].y);
            ctx.strokeStyle = lineColor.replace('0.35', String(alpha * (isDark ? 0.6 : 0.5)));
            ctx.lineWidth = 0.8;
            ctx.stroke();
          }
        }
      }

      // Draw nodes
      nodes.forEach(n => {
        n.pulse += 0.025;
        const pulseR = n.r + Math.sin(n.pulse) * 0.8;

        // Glow
        const grad = ctx.createRadialGradient(n.x, n.y, 0, n.x, n.y, pulseR * 4);
        grad.addColorStop(0, nodeColor);
        grad.addColorStop(1, 'rgba(0,0,0,0)');
        ctx.beginPath();
        ctx.arc(n.x, n.y, pulseR * 4, 0, Math.PI * 2);
        ctx.fillStyle = grad;
        ctx.fill();

        // Core
        ctx.beginPath();
        ctx.arc(n.x, n.y, pulseR, 0, Math.PI * 2);
        ctx.fillStyle = nodeColor;
        ctx.fill();

        // Move
        n.x += n.vx;
        n.y += n.vy;
        if (n.x < 0 || n.x > W) n.vx *= -1;
        if (n.y < 0 || n.y > H) n.vy *= -1;
      });

      // Draw floating binary strings
      binaries.forEach(b => {
        ctx.font = `${b.size}px 'JetBrains Mono', monospace`;
        ctx.fillStyle = binaryColor.replace('0.18', String(b.opacity));
        ctx.fillText(b.char, b.x, b.y);
        b.y += b.vy;
        if (b.y < -20) b.y = H + 10;
        if (b.y > H + 20) b.y = -10;
      });

      animRef.current = requestAnimationFrame(draw);
    }

    draw();

    const handleResize = () => {
      W = window.innerWidth;
      H = window.innerHeight;
      canvas.width = W;
      canvas.height = H;
    };
    window.addEventListener('resize', handleResize);

    return () => {
      cancelAnimationFrame(animRef.current);
      window.removeEventListener('resize', handleResize);
    };
  }, [isDark]);

  return (
    <canvas
      ref={canvasRef}
      id="cyber-canvas"
      aria-hidden="true"
      style={{ opacity: isDark ? 0.65 : 0.5 }}
    />
  );
}

