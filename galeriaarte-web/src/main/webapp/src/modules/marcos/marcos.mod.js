(function (ng) {
    // Definición del módulo
    var mod = ng.module("marcoModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/marcos/';
            // Mostrar la lista de marcos será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/marcosList");
            // Definición del estado 'marcosList' donde se listan los marcos
            $stateProvider.state('marcosList', {
                // Url que aparecerá en el browser
                url: '/marcos/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'marcos.list.html',
                        controller: 'marcoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


