import React, { useState } from 'react';
import { useTheme } from '../context/ThemeContext';
import { profileData } from '../data/portfolioData';

export default function Contact() {
  const { isDark } = useTheme();
  const [formData, setFormData] = useState({ name: '', email: '', subject: '', message: '' });
  const [errors, setErrors]     = useState({});
  const [submitted, setSubmitted] = useState(false);

  const accent   = isDark ? '#D4AF37' : '#16834A';
  const bgCard   = isDark ? '#241132' : '#FFFFFF';
  const bgStat   = isDark ? 'rgba(84,36,119,0.2)' : 'rgba(22,131,74,0.08)';
  const border   = isDark ? 'rgba(212,175,55,0.2)' : 'rgba(22,131,74,0.2)';
  const textMain = isDark ? '#FFFFFF' : '#101B14';
  const textSec  = isDark ? '#C9BFD1' : '#53665A';

  const validate = () => {
    const errs = {};
    if (!formData.name.trim())        errs.name    = 'Name is required';
    if (!formData.email.trim())       errs.email   = 'Email is required';
    else if (!/\S+@\S+\.\S+/.test(formData.email)) errs.email = 'Enter a valid email';
    if (!formData.message.trim())     errs.message = 'Message is required';
    return errs;
  };

  const handleChange = e => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    if (errors[e.target.name]) setErrors({ ...errors, [e.target.name]: '' });
  };

  const handleSubmit = e => {
    e.preventDefault();
    const errs = validate();
    if (Object.keys(errs).length > 0) { setErrors(errs); return; }

    setSubmitted(true);
    const mailtoUrl = `mailto:${profileData.email}?subject=${encodeURIComponent(
      (formData.subject || 'Portfolio Inquiry') + ' - from ' + formData.name
    )}&body=${encodeURIComponent(formData.message + '\n\nSender Email: ' + formData.email)}`;
    window.location.href = mailtoUrl;
  };

  const contactItems = [
    { icon: 'fa-envelope',      href: `mailto:${profileData.email}`,   label: profileData.email,                      ariaLabel: 'Email' },
    { icon: 'fa-phone',         href: `tel:${profileData.phone.replace(/\s+/g,'')}`, label: profileData.phone,        ariaLabel: 'Phone' },
    { icon: 'fa-brands fa-github', href: profileData.github,            label: `github.com/${profileData.githubUsername}`, ariaLabel: 'GitHub', newTab: true },
    { icon: 'fa-brands fa-linkedin', href: profileData.linkedin,        label: 'LinkedIn Profile',                     ariaLabel: 'LinkedIn', newTab: true },
    { icon: 'fa-location-dot',  href: null,                             label: profileData.location,                   ariaLabel: 'Location' },
  ];

  return (
    <section
      id="contact"
      className="py-14 sm:py-20 relative"
      style={{ borderTop: `1px solid ${accent}15` }}
    >
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">

        <div className="grid grid-cols-1 lg:grid-cols-12 gap-12 items-start">

          {/* ════ LEFT: Contact Info ════ */}
          <div className="lg:col-span-5 space-y-6">
            <div className="section-label inline-flex items-center gap-2 mb-2">
              <i className="fa-solid fa-paper-plane"></i> Get in Touch
            </div>
            <h2
              className="section-title text-3xl sm:text-4xl font-extrabold"
            >
              Let's Build Something Secure.
            </h2>
            <p className="text-sm sm:text-base leading-relaxed" style={{ color: textSec }}>
              I'm open to full-time engineering positions, cybersecurity research roles, IoT projects, and technical consultancies. Feel free to connect directly or send a message below.
            </p>

            {/* Resume card */}
            <div
              className="themed-card rounded-2xl p-4 flex flex-col sm:flex-row items-start sm:items-center justify-between gap-3"
              style={{ background: bgCard, border: `1px solid ${border}` }}
            >
              <div className="flex items-center gap-3">
                <i className="fa-solid fa-file-pdf text-3xl" style={{ color: '#ef4444' }}></i>
                <div>
                  <h4 className="text-sm font-bold" style={{ color: textMain }}>HariKrishnan_Resume.pdf</h4>
                  <p className="text-xs" style={{ color: textSec }}>Official Resume • Verified</p>
                </div>
              </div>
              <a
                href={profileData.resumePdfUrl}
                target="_blank"
                rel="noopener noreferrer"
                download="HariKrishnan_Resume.pdf"
                className="btn-primary px-4 py-2 rounded-xl text-xs font-bold flex items-center gap-1.5"
              >
                <i className="fa-solid fa-download text-xs"></i> Download
              </a>
            </div>

            {/* Contact links */}
            <div className="space-y-2">
              {contactItems.map((item, i) => (
                item.href ? (
                  <a
                    key={i}
                    href={item.href}
                    aria-label={item.ariaLabel}
                    target={item.newTab ? '_blank' : undefined}
                    rel={item.newTab ? 'noopener noreferrer' : undefined}
                    className="flex items-center gap-3 p-3.5 rounded-xl transition-all group"
                    style={{
                      background: bgStat,
                      border: `1px solid ${border}`,
                      color: textSec,
                      fontFamily: 'JetBrains Mono, monospace',
                      fontSize: '0.8rem',
                    }}
                    onMouseEnter={e => { e.currentTarget.style.borderColor = accent; e.currentTarget.style.color = accent; }}
                    onMouseLeave={e => { e.currentTarget.style.borderColor = border; e.currentTarget.style.color = textSec; }}
                  >
                    <i className={`fa-solid ${item.icon} text-base`} style={{ color: accent, minWidth: 20 }}></i>
                    <span className="break-all">{item.label}</span>
                  </a>
                ) : (
                  <div
                    key={i}
                    className="flex items-center gap-3 p-3.5 rounded-xl"
                    style={{
                      background: bgStat,
                      border: `1px solid ${border}`,
                      color: textSec,
                      fontFamily: 'JetBrains Mono, monospace',
                      fontSize: '0.8rem',
                    }}
                  >
                    <i className={`fa-solid ${item.icon} text-base`} style={{ color: accent, minWidth: 20 }}></i>
                    <span>{item.label}</span>
                  </div>
                )
              ))}
            </div>
          </div>

          {/* ════ RIGHT: Contact Form ════ */}
          <div className="lg:col-span-7">
            <div
              className="themed-card rounded-3xl p-6 sm:p-8"
              style={{ background: bgCard, border: `1px solid ${border}` }}
            >
              <h3 className="text-xl font-bold mb-1" style={{ fontFamily: 'Space Grotesk, sans-serif', color: textMain }}>
                Send a Message
              </h3>
              <p className="text-xs sm:text-sm mb-6" style={{ color: textSec }}>
                Direct communication to Hari Krishnan.S's primary inbox.
              </p>

              <form onSubmit={handleSubmit} className="space-y-4" noValidate>
                <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                  <div>
                    <label className="block text-xs font-semibold mb-1.5" style={{ color: textSec }}>Your Name *</label>
                    <input
                      type="text"
                      name="name"
                      value={formData.name}
                      onChange={handleChange}
                      placeholder="John Doe"
                      className="form-input w-full rounded-xl px-4 py-2.5 text-sm"
                      style={{ borderColor: errors.name ? '#ef4444' : undefined }}
                    />
                    {errors.name && <p className="text-xs mt-1" style={{ color: '#ef4444' }}>{errors.name}</p>}
                  </div>
                  <div>
                    <label className="block text-xs font-semibold mb-1.5" style={{ color: textSec }}>Your Email *</label>
                    <input
                      type="email"
                      name="email"
                      value={formData.email}
                      onChange={handleChange}
                      placeholder="john@example.com"
                      className="form-input w-full rounded-xl px-4 py-2.5 text-sm"
                      style={{ borderColor: errors.email ? '#ef4444' : undefined }}
                    />
                    {errors.email && <p className="text-xs mt-1" style={{ color: '#ef4444' }}>{errors.email}</p>}
                  </div>
                </div>

                <div>
                  <label className="block text-xs font-semibold mb-1.5" style={{ color: textSec }}>Subject</label>
                  <input
                    type="text"
                    name="subject"
                    value={formData.subject}
                    onChange={handleChange}
                    placeholder="Project Discussion / Job Opportunity"
                    className="form-input w-full rounded-xl px-4 py-2.5 text-sm"
                  />
                </div>

                <div>
                  <label className="block text-xs font-semibold mb-1.5" style={{ color: textSec }}>Message *</label>
                  <textarea
                    rows={5}
                    name="message"
                    value={formData.message}
                    onChange={handleChange}
                    placeholder="Hi Hari Krishnan.S, I reviewed your SATLINK and CyberResilience projects…"
                    className="form-input w-full rounded-xl px-4 py-2.5 text-sm resize-none"
                    style={{ borderColor: errors.message ? '#ef4444' : undefined }}
                  />
                  {errors.message && <p className="text-xs mt-1" style={{ color: '#ef4444' }}>{errors.message}</p>}
                </div>

                <button
                  type="submit"
                  className="btn-primary w-full py-3.5 rounded-xl font-bold flex items-center justify-center gap-2"
                >
                  <i className="fa-solid fa-paper-plane text-sm"></i>
                  <span>Send Message via Email Client</span>
                </button>

                {submitted && (
                  <div
                    className="p-4 rounded-xl flex items-center gap-3 text-sm"
                    style={{
                      background: isDark ? 'rgba(34,197,94,0.08)' : 'rgba(22,131,74,0.08)',
                      border: '1px solid rgba(34,197,94,0.3)',
                      color: '#22c55e',
                    }}
                  >
                    <i className="fa-solid fa-circle-check text-xl"></i>
                    <div>
                      <div className="font-bold">Email Client Opened!</div>
                      <div className="text-xs opacity-80">Your default email client should have opened to send the message directly.</div>
                    </div>
                  </div>
                )}
              </form>
            </div>
          </div>

        </div>
      </div>
    </section>
  );
}
