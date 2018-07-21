const PROXY_CONFIG = [
    {
        context: [
            "/auth/**",
            "/admin/**",
            "/auth/*",
            "/admin/*"
        ],
        target: "http://localhost:8080",
        secure: false,
        "logLevel": "debug"
    }
]

module.exports = PROXY_CONFIG;