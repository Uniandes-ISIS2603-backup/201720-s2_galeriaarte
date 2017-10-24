(function (ng) {
    // Definición del módulo
    var mod = ng.module("blogModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/blogs/';
            // Mostrar la lista de blogs será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/blogsList");
            // Definición del estado 'blogsList' donde se listan los blogs
            $stateProvider.state('blogsList', {
                // Url que aparecerá en el browser
                url: '/blogs/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'blogs.list.html',
                        controller: 'blogCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


