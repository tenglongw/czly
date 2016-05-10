/*global module:false*//*jshint unused:false*/

'use strict';
module.exports = function(grunt) {

    var config = {
        'iidxdist': './dist/iidx'
    };
    var pkgJson = require('./package.json');
    var versionnum = grunt.option( 'versionnum') ? grunt.option( 'versionnum') : pkgJson.version;
    var buildnum = String(grunt.option( 'buildnum') ? grunt.option( 'buildnum') : 'xxxx');

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'), config: config,

    clean: {
      bower: ['components'],
      css: ['css'],
      test: ['test/unit/report*', 'test/ui/report*'],
      dist: ['dist']
    },

    // Install bower components
    shell: {
        options: {
            stdout: true,
            stderr: true
        },
        'bower-install': {
            command: 'bower install',
            options: {
                stdout: true
            }
        }
    },

    copy: {
        "dist-iidx": {
          files: [
              {
                  expand: true,
                  flatten:true,
                  cwd: './',
                  src: ['css/ge-bootstrap-iidx.min.css'],
                  dest: '<%= config.iidxdist %>'
              },
              {
                  expand: true,
                  cwd: './',
                  src: ['docs/**'],
                  dest: '<%= config.iidxdist %>'
              },
              {
                  expand: true,
                  cwd: './components/brandkit',
                  src: ['fonts/**'],
                  dest: '<%= config.iidxdist %>/fonts'
              },
          ]
        }
    },

    less: {
      compile: {
        options: {
          paths: ['less/', 'components/']
        },
        files: [{
            expand: true,
            cwd: 'less/',
            src: ['{iidx-bootstrap,hdx-bootstrap,hdx-bootstrap-lights-off,cdx-bootstrap,hdx-responsive,iidx-responsive}.less'],
            dest: 'css/',
            ext: '.css',
            nonull: true
        },
        {
            expand: true,
            cwd: 'less/bootstrap-standalone',
            src: ['*.less'],
            dest: 'css/',
            ext: '.css',
            nonull: true
        }]
      },
        minify: {
            options: {
                paths: ['less/', 'components/'],
                yuicompress: true
            },
            files: [{
                    expand: true,
                    cwd: 'less/',
                    src: ['{iidx-bootstrap,hdx-bootstrap,cdx-bootstrap,hdx-responsive,iidx-responsive}.less'],
                    dest: 'css/',
                    ext: '.min.css',
                    nonull: true
                },
                {
                    expand: true,
                    cwd: 'less/bootstrap-standalone',
                    src: ['*.less'],
                    dest: 'css/',
                    ext: '.min.css',
                    nonull: true
                }

            ]
        }
    },

    watch: {
      component: {
        files: ['less/**/*', 'js/**/*', 'test/**/*'],
        tasks: 'default',
        options: {
          interrupt: true
        }
      }
    },

    tests: {
      files: ['test/**/*'],
      tasks: 'test',
      options: {
        interrupt: true
      }
    },

    jshint: {
        all: [
            'src/**/*.js',
            'Gruntfile.js'
        ],
        options: {
            jshintrc: '.jshintrc'
        }
    },

    karma: {
        unit: {
          configFile: 'test/unit/karma.conf.js'
        }
    },
    webdriver: {
          options: {
              specFiles: ['test/ui/**/*spec.js']
          },
	  local: {
	  	webdrivers: ['phantomjs']
	   }
    },

    compress: {
      iidx: {
          options: {
              archive: '<%= config.iidxdist %>/ge-bootstrap-iidx-' + versionnum + '.' + buildnum + '.zip'
          },
          expand: true,
          cwd: '<%= config.iidxdist %>',
          src: ['**/*'],
          dest: '<%= config.iidxdist %>'
      }
    },

    // require js optimization
    requirejs: {
      compile: {
          options: {
              name: "build-utils/ge-bootstrap-require",
              baseUrl: "",
              include: "build-utils/almond",
              mainConfigFile: "build-utils/ge-bootstrap-require.js",
              out: "<%= config.iidxdist %>/ge-bootstrap.min.js"
          }
      },
      'compile-amd': {
          options: {
              name: "build-utils/ge-bootstrap-require",
              baseUrl: "",
              mainConfigFile: "build-utils/ge-bootstrap-require.js",
              out: "<%= config.iidxdist %>/ge-bootstrap-amd.min.js"
          }
      }
    }

  });

    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-requirejs');
    grunt.loadNpmTasks('grunt-contrib-compress');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-shell');
    grunt.loadNpmTasks('grunt-karma');
    grunt.loadNpmTasks('grunt-shell');
    grunt.loadNpmTasks('webdriver-support');

    // Default task.
    grunt.registerTask('default', 'Build the LESS for testing', [
        'clean',
        'shell:bower-install',
        'less',
        'test'
    ]);

    // Default task.
    grunt.registerTask('dist', 'Build distribution', [
        'default',
        'test',
        'requirejs:compile',
        'requirejs:compile-amd',
        'copy:dist-iidx',
        'compress:iidx'
    ]);
    
    var webdriver = grunt.option('webdriver') || 'phantomjs';
    var reporter = grunt.option('reporter') || 'spec';

    grunt.registerTask('test', 'Run tests for this component.', [
        'clean:test',
        'less',
        //'jshint',
        'karma',
        'webdriver'
    ]);
};
