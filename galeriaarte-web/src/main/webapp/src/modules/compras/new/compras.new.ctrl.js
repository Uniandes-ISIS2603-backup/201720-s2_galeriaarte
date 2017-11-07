(function (ng) {
    var mod = ng.module("compraModule");
    mod.constant("comprasContext", "api/compras");
    mod.controller('compraNewCtrl', ['$scope', '$http', 'comprasContext', '$state', '$rootScope',
<<<<<<< HEAD
        function ($scope, $http, comprasContext, $state, pagosContext, $rootScope) {
            $rootScope.edit = false;
            $scope.createCompra = function () {
                $http.post(comprasContext, {
                    id: 0,
                    valor: $scope.compraValor,
                    fecha: $scope.compraFecha
                }).then(function (response) {
                    
=======
        function ($scope, $http, comprasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createCompra = function () {
                $http.post(comprasContext, {
                    name: $scope.compraName,
                    contenido: $scope.compraContenido
                }).then(function (response) {
                    //Blog created successfully
>>>>>>> origin/master
                    $state.go('comprasList', {compraId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


