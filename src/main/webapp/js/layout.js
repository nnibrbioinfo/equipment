

var classCallCheck = function (instance, Constructor) {
  if (!(instance instanceof Constructor)) {
    throw new TypeError("Cannot call a class as a function");
  }
};

var Layout = function ($) {
  /**
   * Constants
   * ====================================================
   */

  var NAME = 'Layout';
  var DATA_KEY = 'lte.layout';
  var JQUERY_NO_CONFLICT = $.fn[NAME];

  var Selector = {
    SIDEBAR: '.main-sidebar',
    HEADER: '.main-header',
    CONTENT: '.content-wrapper',
    CONTENT_HEADER: '.content-header',
    WRAPPER: '.wrapper',
    CONTROL_SIDEBAR: '.control-sidebar',
    LAYOUT_FIXED: '.layout-fixed',
    FOOTER: '.main-footer'
  };

  var ClassName = {
    HOLD: 'hold-transition',
    SIDEBAR: 'main-sidebar',
    LAYOUT_FIXED: 'layout-fixed'

    /**
     * Class Definition
     * ====================================================
     */

  };
  var Layout = function () {
    function Layout(element) {
      classCallCheck(this, Layout);

      this._element = element;

      this._init();
    }

    // Public

    Layout.prototype.fixLayoutHeight = function fixLayoutHeight() {
      var heights = {
        window: $(window).height(),
        header: $(Selector.HEADER).outerHeight(),
        footer: $(Selector.FOOTER).outerHeight(),
        sidebar: $(Selector.SIDEBAR).height()
      };
      var max = this._max(heights);

      $(Selector.CONTENT).css('min-height', max - heights.header - heights.footer);
      // $(Selector.SIDEBAR).css('min-height', max - heights.header - heights.footer);
    };

    // Private

    Layout.prototype._init = function _init() {
      var _this = this;

      // Enable transitions
      $('body').removeClass(ClassName.HOLD);

      // Activate layout height watcher
      this.fixLayoutHeight();

      $(window).resize(function () {
        _this.fixLayoutHeight();
      });

      $('body, html').css('height', 'auto');
    };

    Layout.prototype._max = function _max(numbers) {
      // Calculate the maximum number in a list
      var max = 0;

      Object.keys(numbers).forEach(function (key) {
        if (numbers[key] > max) {
          max = numbers[key];
        }
      });

      return max;
    };

    // Static

    Layout._jQueryInterface = function _jQueryInterface(operation) {
      return this.each(function () {
        var data = $(this).data(DATA_KEY);

        if (!data) {
          data = new Layout(this);
          $(this).data(DATA_KEY, data);
        }

        if (operation) {
          data[operation]();
        }
      });
    };

    return Layout;
  }();

  /**
   * Data API
   * ====================================================
   */


  $(window).on('load', function () {
    Layout._jQueryInterface.call($('body'));
  });

  /**
   * jQuery API
   * ====================================================
   */

  $.fn[NAME] = Layout._jQueryInterface;
  $.fn[NAME].Constructor = Layout;
  $.fn[NAME].noConflict = function () {
    $.fn[NAME] = JQUERY_NO_CONFLICT;
    return Layout._jQueryInterface;
  };

  return Layout;
}(jQuery);