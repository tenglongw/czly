define(['jquery', 'd3-amd'], function ($, d3) {
  'use strict';
  $.sparkline = {
    // Color classes.
    // The actual colors are defined in the sparklines CSS.
    colors: {
      normal: [
        'normal-a',
        'normal-b',
        'normal-c'
      ],
      attention: [
        'attention-a'
      ],
      warning: [
        'warning-a'
      ],
      critical: [
        'critical-a'
      ]
    },

    getColorClass: function (thresholds, index, value) {
      var colors = $.sparkline.colors.normal,
        thresholdNames = ['critical', 'warning', 'attention'];

      for (var i = 0; i < thresholdNames.length; i++) {
        var n = thresholdNames[i],
          t = thresholds[n];

        if (-1 < t && value >= t) {
          colors = $.sparkline.colors[n];
          break;
        }
      }

      return colors[index % colors.length];
    },

    // Default settings for a graph definition
    defaults: {
      // The data, an array of numbers
      data: [],

      // 'bar', 'area', 'bullet'
      type: 'bar',

      // The start of the y-axis
      min: 0,

      // The end of the y-axis
      max: 1,

      // These are thresholds for values. If a value
      // exceeds the given threshold, the color of the
      // graph will change to match the given state.
      // A -1 means there is no threshold.
      thresholds: {
        attention: -1,
        warning: -1,
        critical: -1
      }
    },

    // The functions that actually create the graphs
    graph: {
      bar: function (container, w, h, layer, y) {
        y = y ? y : d3.scale.linear()
          .domain([layer.min, layer.max])
          .range([0, h]);

        var x = d3.scale.ordinal()
          .domain(d3.range(layer.values.length))
          .rangeRoundBands([0, w], 0.2),
          width = x.rangeBand(),
          y1 = function (d) {
            return h - y(d.y0 + d.y);
          },
          height = function (d) {
            return y(d.y);
          };

        container
          .append('g')
          .attr('class', 'bar')
          .attr('width', w)
          .attr('height', h)
          .attr('x', 0)
          .attr('y', 0)
          .selectAll('rect')
          .data(layer.values)
          .enter()
          .append('rect')
          .attr('x', function (d) {
            return x(d.x);
          })
          .attr('width', width)
          .attr('y', y1)
          .attr('height', height)
          .attr('class', function (d) {
            return d.colorClass;
          });
      },

      area: function (container, w, h, layer, y) {
        y = y ? y : d3.scale.linear()
          .domain([layer.min, layer.max])
          .range([0, h]);
        var x = d3.scale.linear()
          .domain([0, layer.values.length - 1])
          .range([0, w]);
        var area = d3.svg.area()
          .x(function (d) {
            return x(d.x);
          })
          .y1(function (d) {
            return h - y(d.y0 + d.y);
          })
          .y0(function (d) {
            return h - y(d.y0);
          }),
          line = d3.svg.line()
            .x(function (d) {
              return x(d.x);
            })
            .y(function (d) {
              return h - y(d.y0 + d.y);
            }),
          group = container.append('g');


        var makeArea = function (data, cls) {
          group.append('path').attr('d', area(data)).attr('class', 'area ' + cls),
          group.append('path').attr('d', line(data)).attr('class', 'line ' + cls);
        };

        makeArea(
          layer.values,
          $.sparkline.colors.normal[layer.index % $.sparkline.colors.normal.length]);

        var thresholds = ['attention', 'warning', 'critical'];

        for (var i = 0; i < thresholds.length; i++) {
          var n = thresholds[i],
            t = layer.thresholds[n];

          if (-1 < t) {
            var chunks = [],
              currentChunk = [];

            for (var j = 0; j < layer.values.length; j++) {
              if (layer.values[j].y >= t) {
                currentChunk.push(layer.values[j]);
              }
              else if (currentChunk.length > 0) {
                // We use "1" here instead of "0" because
                // A single point won't draw anything anyway
                if (currentChunk.length > 1) {
                  chunks.push(currentChunk);
                }

                currentChunk = [];
              }
            }

            if (currentChunk.length > 1) {
              chunks.push(currentChunk);
            }

            if (chunks.length > 0) {
              var cls = $.sparkline.colors[n][layer.index % $.sparkline.colors[n].length];

              $.each(chunks, function () {
                makeArea(this, cls);
              });
            }
          }
        }
      },

      bullet: function (container, w, h, layer, y) {
        var x = d3.scale.linear()
          .domain([layer.min, layer.max])
          .rangeRound([0, w]);

        container
          .append('g')
          .attr('class', 'bullet')
          .attr('width', w)
          .attr('height', h)
          .attr('x', 0)
          .attr('y', 0)
          .append('rect')
          .attr('width', x(layer.values[0].y))
          .attr('height', '8')
          .attr('class', layer.values[0].colorClass);

      }
    }
  };

  // Creates a sparkline inside each matched element. The dimensions of the
  // sparkline are the same as its parent element. This function can take
  // a single graph definition or an array of them.
  $.fn.sparkline = function (graphs, stacked) {
    return this.each(function () {
      var $source = $(this),
        w = $source.width(),
        h = $source.height(),
        $container = $('<div class="sparkline-generated-container"></div>')
          .insertBefore($source)
          .css({
            width: w,
            height: h
          }),
        container = d3.select($container.get(0))
          .append('svg')
          .attr('width', w)
          .attr('height', h),
        layers = [];


      $.each(graphs, function (i) {
        var graph = this;
        layers.push($.extend(this, {
          index: i,
          handler: $.sparkline.graph[graph.type],
          values: $.map(graph.data, function (d, j) {
            return {
              x: j,
              y: d,
              y0: graph.min,
              colorClass: $.sparkline.getColorClass(graph.thresholds, i, d)
            };
          })
        }));
      });

      var y;

      if (stacked) {
        d3.layout.stack().values(function (d) {
          return d.values;
        })(layers);

        var min = d3.min(layers, function (layer) {
          return layer.min;
        });
        var max = d3.max(layers, function (a) {
          return d3.max(a.values, function (b) {
            return b.y + b.y0;
          });
        });

        y = d3.scale.linear()
          .domain([min, max])
          .range([0, h]);
      }

      $.each(layers, function () {
        this.handler(container, w, h, this, y);
      });
    });
  };

  // Finds html-structured sparklines and draws them
  $(function () {
    $('ul.sparkline').each(function () {
      var graphs = [],
        $container = $(this);

      $container
        .find('>li>ul')
        .each(function () {
          var $ul = $(this);

          var data = [];

          $ul.find('>li').each(function () {
            data.push(parseFloat($(this).text()));
          });

          graphs.push({
            data: data,
            type: $ul.data('type') || $.sparkline.defaults.type,
            min: typeof $ul.data('min') !== 'undefined' ? parseFloat($ul.data('min')) : $.sparkline.defaults.min,
            max: typeof $ul.data('max') !== 'undefined' ? parseFloat($ul.data('max')) : $.sparkline.defaults.max,
            thresholds: {
              attention: typeof $ul.data('threshold-attention') !== 'undefined' ? parseFloat($ul.data('threshold-attention')) : $.sparkline.defaults.thresholds.attention,
              warning: typeof $ul.data('threshold-warning') !== 'undefined' ? parseFloat($ul.data('threshold-warning')) : $.sparkline.defaults.thresholds.warning,
              critical: typeof $ul.data('threshold-critical') !== 'undefined' ? parseFloat($ul.data('threshold-critical')) : $.sparkline.defaults.thresholds.critical
            }
          });
        })
        .end()
        .sparkline(graphs, $container.is('.stacked'));
    });
  });
});
