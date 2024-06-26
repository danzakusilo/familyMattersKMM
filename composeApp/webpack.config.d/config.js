const TerserPlugin = require("terser-webpack-plugin");
const NodePolyfillPlugin = require("node-polyfill-webpack-plugin");
config.plugins.push(
  new NodePolyfillPlugin()
)
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
config.resolve = {
    fallback: {
        fs: false,
        path: false,
        crypto: false,
    },
};