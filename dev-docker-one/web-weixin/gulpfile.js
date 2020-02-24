var gulp = require('gulp');
var connect = require('gulp-connect');
var proxy = require('http-proxy-middleware');

// 文件复制到dist 文件夹
gulp.task('hrframe', function () {
    gulp.src(["web/*", "web/**/*"])
        .pipe(gulp.dest('dist/'));
    gulp.src(["src/*", "src/**/*"])
        .pipe(gulp.dest('dist/app'));
    gulp.src(["doc/*", "doc/**/*"])
        .pipe(gulp.dest('dist/doc'));
});

// 监听文件修改，自动执行hrframe 任务
gulp.task('watch', function () {
    gulp.watch(['src/*', 'src/**/*', 'web/*', 'web/**/*', 'doc/*', "doc/**/*"], ['hrframe']);
});

// 执行发布任务
gulp.task("release", function () {
    gulp.src(["web/**/*", "web/**/*"])
        .pipe(gulp.dest('release/'));
    gulp.src(["src/**/*", "src/**/*"])
        .pipe(gulp.dest('release/app'));
});

// 创建一个服务器，并设置代理
gulp.task('server', function (done) {
    connect.server({

        host: '192.168.1.103',

        port: '3000',
        root: 'dist',
        livereload: false,
        middleware: function (connect, opt) {
            var backend = proxy('/proxy', {
                target: 'http://localhost:4000',
                changeOrigin: true,
                pathRewrite: {
                    '/proxy/': ''
                }
            });
            return [backend];
        }
    });
});

// 执行这三个任务
gulp.task("default", ['hrframe', 'watch', 'server']);