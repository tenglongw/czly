require.config(
    {
        shim: {
            'bootstrap-timepicker': ['jquery']
        }
    });

define(
    ['jquery', 'oo-utils', 'bootstrap-timepicker'], function ($, ooUtils) {

        'use strict';
        var isTouchDevice = 'ontouchstart' in document.documentElement;

        ooUtils.makePlugin(
            "ge-timepicker", {
                init: function (options, elm) {
                    $(elm).timepicker(options);
                    return this;
                }
            });

        $('.timepicker').each(function () {
            var currentControl = $(this);
            $(this).timepicker({
                explicitMode: true,
                retainInvalid: true
            });

            if (!isTouchDevice) {
                var btn = currentControl
                    .next('.btn.add-on');

                if (btn.length === 0) {
                    btn = currentControl
                        .next('.add-on,.input-group-btn')
                        .find(".btn");
                }

                btn.click(
                    function () {
                        (currentControl).focus();
                    });
            }
        });
    });
