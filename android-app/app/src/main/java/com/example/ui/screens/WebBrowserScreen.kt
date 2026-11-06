package com.example.ui.screens

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.webkit.*
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ui.theme.AccentTeal
import com.example.ui.theme.CyanPrimary

enum class ViewportMode(val title: String, val icon: String, val widthDp: Int?) {
    LAPTOP("Laptop / Desktop", "💻", null),
    IPAD("iPad / Tablet", "📱", 768),
    SMARTPHONE("Smartphone", "📲", 390)
}

enum class RenderEngine {
    WEB_ENGINE, // Real HTML5 / Tailwind Web App via WebView
    COMPOSE_WEB // Responsive Web Layout in Compose
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebBrowserScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var webViewRef by remember { mutableStateOf<WebView?>(null) }
    var currentUrl by remember { mutableStateOf("https://harikrishnan.dev") }
    var pageTitle by remember { mutableStateOf("Harikrishnan S | Cybersecurity & Software Engineer") }
    var isLoading by remember { mutableStateOf(false) }
    var canGoBack by remember { mutableStateOf(false) }
    var canGoForward by remember { mutableStateOf(false) }

    var selectedViewport by remember { mutableStateOf(ViewportMode.LAPTOP) }
    var renderEngine by remember { mutableStateOf(RenderEngine.WEB_ENGINE) }
    var showViewportMenu by remember { mutableStateOf(false) }

    // Intercept back button to navigate within web history
    BackHandler(enabled = canGoBack) {
        webViewRef?.goBack()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF070B12)) // Dark sleek browser background
    ) {
        // ==================== BROWSER TOP CHROME & TAB BAR ====================
        Surface(
            color = Color(0xFF0F172A),
            modifier = Modifier.fillMaxWidth(),
            shadowElevation = 4.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Top Tab Bar with Window Controls
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Window Traffic Light Dots (Mac / Desktop style)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(10.dp).background(Color(0xFFFF5F56), CircleShape))
                        Box(modifier = Modifier.size(10.dp).background(Color(0xFFFFBD2E), CircleShape))
                        Box(modifier = Modifier.size(10.dp).background(Color(0xFF27C93F), CircleShape))
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Active Website Tab
                    Surface(
                        color = Color(0xFF1E293B),
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(28.dp)
                            .testTag("browser_active_tab")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            Text(
                                text = "🌐",
                                fontSize = 11.sp
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = pageTitle,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f)
                            )
                            if (isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(12.dp),
                                    strokeWidth = 1.5.dp,
                                    color = CyanPrimary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Viewport Switcher Pills (Laptop / iPad / Smartphone)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ViewportMode.values().forEach { mode ->
                            val isSelected = selectedViewport == mode
                            Surface(
                                color = if (isSelected) CyanPrimary else Color(0xFF1E293B),
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier
                                    .clickable { selectedViewport = mode }
                                    .testTag("viewport_${mode.name.lowercase()}")
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = mode.icon,
                                        fontSize = 11.sp
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = mode.name.take(4),
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) Color.Black else Color.White
                                    )
                                }
                            }
                        }
                    }
                }

                // Omnibox / Browser URL Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Back Button
                    IconButton(
                        onClick = { webViewRef?.goBack() },
                        enabled = canGoBack,
                        modifier = Modifier.size(30.dp).testTag("browser_back_btn")
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = if (canGoBack) Color.White else Color(0xFF64748B),
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    // Forward Button
                    IconButton(
                        onClick = { webViewRef?.goForward() },
                        enabled = canGoForward,
                        modifier = Modifier.size(30.dp).testTag("browser_forward_btn")
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Forward",
                            tint = if (canGoForward) Color.White else Color(0xFF64748B),
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    // Reload Button
                    IconButton(
                        onClick = {
                            if (renderEngine == RenderEngine.WEB_ENGINE) {
                                webViewRef?.reload()
                            }
                            Toast.makeText(context, "Reloading Website...", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.size(30.dp).testTag("browser_reload_btn")
                    ) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Reload",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    // URL Address Bar (Omnibox)
                    Surface(
                        color = Color(0xFF0A0E17),
                        shape = RoundedCornerShape(20.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155)),
                        modifier = Modifier
                            .weight(1f)
                            .height(34.dp)
                            .testTag("browser_omnibox")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        ) {
                            // SSL Security Lock
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = "SSL Secure",
                                tint = Color(0xFF10B981), // Emerald Secure Green
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = "https://",
                                fontSize = 12.sp,
                                color = Color(0xFF64748B),
                                fontFamily = FontFamily.Monospace
                            )
                            Text(
                                text = "harikrishnan.dev",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = CyanPrimary,
                                fontFamily = FontFamily.Monospace,
                                modifier = Modifier.weight(1f)
                            )

                            // Copy Link Action
                            IconButton(
                                onClick = {
                                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                    clipboard.setPrimaryClip(ClipData.newPlainText("Website URL", "https://harikrishnan.dev"))
                                    Toast.makeText(context, "Website URL copied to clipboard!", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    Icons.Default.ContentCopy,
                                    contentDescription = "Copy URL",
                                    tint = Color(0xFF94A3B8),
                                    modifier = Modifier.size(13.dp)
                                )
                            }
                        }
                    }

                    // Engine Mode Toggle (HTML5 Web vs Compose Web)
                    Surface(
                        color = Color(0xFF1E293B),
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155)),
                        modifier = Modifier
                            .clickable {
                                renderEngine = if (renderEngine == RenderEngine.WEB_ENGINE) {
                                    RenderEngine.COMPOSE_WEB
                                } else {
                                    RenderEngine.WEB_ENGINE
                                }
                            }
                            .testTag("engine_toggle_btn")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = if (renderEngine == RenderEngine.WEB_ENGINE) "🌐 HTML5 Web" else "⚡ Native Web",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (renderEngine == RenderEngine.WEB_ENGINE) CyanPrimary else AccentTeal
                            )
                        }
                    }

                    // Open in Chrome / External Browser
                    IconButton(
                        onClick = {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ais-dev-7jei5b3besiat275d547oj-708760793459.asia-east1.run.app"))
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "Opening website in browser...", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.size(30.dp).testTag("open_external_browser_btn")
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.OpenInNew,
                            contentDescription = "Open in Web Browser",
                            tint = CyanPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    // Download Resume PDF
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Opening Harikrishnan's Resume (PDF)...", Toast.LENGTH_LONG).show()
                        },
                        modifier = Modifier.size(30.dp).testTag("download_resume_header_btn")
                    ) {
                        Icon(
                            Icons.Default.Download,
                            contentDescription = "Resume PDF",
                            tint = AccentTeal,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        // ==================== RESPONSIVE VIEWPORT STAGE ====================
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color(0xFF060911)),
            contentAlignment = Alignment.TopCenter
        ) {
            // Apply width constraints based on selected viewport (Laptop, iPad, Smartphone)
            val containerModifier = when (selectedViewport) {
                ViewportMode.LAPTOP -> Modifier.fillMaxSize()
                ViewportMode.IPAD -> Modifier
                    .width(768.dp)
                    .fillMaxHeight()
                    .padding(vertical = 12.dp)
                    .shadow(16.dp, RoundedCornerShape(16.dp))
                    .border(2.dp, Color(0xFF1E293B), RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))

                ViewportMode.SMARTPHONE -> Modifier
                    .width(390.dp)
                    .fillMaxHeight()
                    .padding(vertical = 12.dp)
                    .shadow(20.dp, RoundedCornerShape(24.dp))
                    .border(3.dp, Color(0xFF1E293B), RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp))
            }

            Box(
                modifier = containerModifier.background(Color(0xFF0A0E17))
            ) {
                if (renderEngine == RenderEngine.WEB_ENGINE) {
                    // Full HTML5 + Tailwind Web App Engine
                    AndroidView(
                        factory = { ctx ->
                            WebView(ctx).apply {
                                webViewRef = this
                                settings.apply {
                                    javaScriptEnabled = true
                                    domStorageEnabled = true
                                    loadWithOverviewMode = true
                                    useWideViewPort = true
                                    setSupportZoom(true)
                                    builtInZoomControls = true
                                    displayZoomControls = false
                                    allowFileAccess = true
                                    allowContentAccess = true
                                    databaseEnabled = true
                                    cacheMode = WebSettings.LOAD_DEFAULT
                                    // Custom Desktop/Tablet/Mobile user agent simulation
                                    userAgentString = when (selectedViewport) {
                                        ViewportMode.LAPTOP -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
                                        ViewportMode.IPAD -> "Mozilla/5.0 (iPad; CPU OS 17_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Mobile/15E148 Safari/604.1"
                                        ViewportMode.SMARTPHONE -> "Mozilla/5.0 (Linux; Android 14) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36"
                                    }
                                }

                                webViewClient = object : WebViewClient() {
                                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                                        isLoading = true
                                        canGoBack = view?.canGoBack() == true
                                        canGoForward = view?.canGoForward() == true
                                    }

                                    override fun onPageFinished(view: WebView?, url: String?) {
                                        isLoading = false
                                        canGoBack = view?.canGoBack() == true
                                        canGoForward = view?.canGoForward() == true
                                        pageTitle = view?.title ?: "Harikrishnan S | Cybersecurity & Software Engineer"
                                    }

                                    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                                        val url = request?.url?.toString() ?: return false
                                        return when {
                                            url.startsWith("tel:") || url.startsWith("mailto:") -> {
                                                try {
                                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                                    ctx.startActivity(intent)
                                                } catch (e: Exception) {
                                                    Toast.makeText(ctx, "No app available to handle this action", Toast.LENGTH_SHORT).show()
                                                }
                                                true
                                            }
                                            url.endsWith(".pdf") -> {
                                                Toast.makeText(ctx, "Downloading Harikrishnan's Resume (PDF)...", Toast.LENGTH_LONG).show()
                                                true
                                            }
                                            else -> false
                                        }
                                    }
                                }

                                webChromeClient = object : WebChromeClient() {
                                    override fun onReceivedTitle(view: WebView?, title: String?) {
                                        if (!title.isNullOrEmpty()) {
                                            pageTitle = title
                                        }
                                    }
                                }

                                setDownloadListener { _, _, _, _, _ ->
                                    Toast.makeText(ctx, "Downloading Harikrishnan's Resume (PDF)...", Toast.LENGTH_LONG).show()
                                }

                                // Load the bundled standalone responsive HTML website
                                loadUrl("file:///android_asset/index.html")
                            }
                        },
                        update = { webView ->
                            webViewRef = webView
                        },
                        modifier = Modifier.fillMaxSize().testTag("webview_website")
                    )
                } else {
                    // Native Compose Web Layout
                    HomeScreen(modifier = Modifier.fillMaxSize())
                }

                // Viewport indicator badge at bottom right
                Surface(
                    color = Color(0xCC0F172A),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x33FFFFFF)),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "${selectedViewport.icon} ${selectedViewport.title}",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = CyanPrimary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (renderEngine == RenderEngine.WEB_ENGINE) "• HTML5/Tailwind" else "• Compose Web",
                            fontSize = 10.sp,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }
            }
        }
    }
}
