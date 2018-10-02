var $lazyConfig = {
    scrollDirection: 'vertical',
    effect: "fadeIn",
    effectTime: 400,
    threshold: 0,
    visibleOnly: true,
    onError: function(element) {
        console.log('error loading ' + element.data('src'));
    },
    beforeLoad: function(e) {
                // before load, store the gradient onto the element data
                e.data("gradient", e.css("background-image"));
    },
    afterLoad: function(e) {
        // afterwards create the wanted background image
        e.css("background-image", e.data("gradient") + "," + e.css("background-image"));
    }
};

$('.lazy').Lazy($lazyConfig);

