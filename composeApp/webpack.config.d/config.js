const TerserPlugin = require("terser-webpack-plugin");

config.optimization = config.optimization || {};
config.optimization.minimize = false;
config.optimization.minimizer = [
    new TerserPlugin({
        terserOptions: {
            mangle: false,    // Note: By default, mangle is set to true.
            compress: false, // Disable the transformations that reduce the code size.
            output: {
                beautify: true,
            },
        },
    }),
];