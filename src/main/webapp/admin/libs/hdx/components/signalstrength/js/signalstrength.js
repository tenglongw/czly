define(['jquery'], function ($) {
    'use strict';

    var signalStrength = $('.signalstrength');

    signalStrength.find('.indicator[data-value="0"]').append('<i class="icon-ico_circle_sm"></i><i class="icon-ico_circle_sm"></i><i class="icon-ico_circle_sm"></i><i class="icon-ico_circle_sm"></i><i class="icon-ico_circle_sm"></i><i class="icon-ico_circle_sm"></i>');
    signalStrength.find('.indicator[data-value="1"]').append('<i class="icon-ico_circle_sm error"></i><i class="icon-ico_circle_sm"></i><i class="icon-ico_circle_sm"></i><i class="icon-ico_circle_sm"></i><i class="icon-ico_circle_sm"></i><i class="icon-ico_warning_sm error"></i>');
    signalStrength.find('.indicator[data-value="2"]').append('<i class="icon-ico_circle_sm error"></i><i class="icon-ico_circle_sm error"></i><i class="icon-ico_circle_sm"></i><i class="icon-ico_circle_sm"></i><i class="icon-ico_circle_sm"></i><i class="icon-ico_warning_sm error"></i>');
    signalStrength.find('.indicator[data-value="3"]').append('<i class="icon-ico_circle_sm warning"></i><i class="icon-ico_circle_sm warning"></i><i class="icon-ico_circle_sm warning"></i><i class="icon-ico_circle_sm"></i><i class="icon-ico_circle_sm"></i><i class="icon-ico_alert_sm warning"></i>');
    signalStrength.find('.indicator[data-value="4"]').append('<i class="icon-ico_circle_sm success"></i><i class="icon-ico_circle_sm success"></i><i class="icon-ico_circle_sm success"></i><i class="icon-ico_circle_sm success"></i><i class="icon-ico_circle_sm success"></i><i class="icon-ico_circle_sm"></i>');
    signalStrength.find('.indicator[data-value="5"]').append('<i class="icon-ico_circle_sm success"></i><i class="icon-ico_circle_sm success"></i><i class="icon-ico_circle_sm success"></i><i class="icon-ico_circle_sm success"></i><i class="icon-ico_circle_sm success"></i><i class="icon-ico_circle_sm success"></i>');

});
