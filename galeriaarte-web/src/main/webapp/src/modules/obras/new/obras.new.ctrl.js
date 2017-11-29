(function (ng) {
    var mod = ng.module("obraModule");
    mod.constant("obrasContext", "api/obras");
    mod.controller('obraNewCtrl', ['$scope', '$http', 'obrasContext', '$state', '$rootScope',
        function ($scope, $http, obrasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createObra = function () {
                $http.post(obrasContext, {
                    id: $scope.obraId,
                    name: $scope.obraName,
                    tipo: $scope.obraTipo,
                    cantidad: $scope.obraCantidad,
                    valor: $scope.obraValor,
                    imagen: $scope.obraImagen
                }).then(function (response) {
                    //Obra created successfully
                    $state.go('obrasList', {obraId: response.data.id}, {reload: true});
                });
            };
            
        }
    ]);
}
)(angular);


