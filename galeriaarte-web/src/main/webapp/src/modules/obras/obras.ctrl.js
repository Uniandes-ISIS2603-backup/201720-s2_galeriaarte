/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("obraModule");
    mod.constant("obrasContext", "api/obras");
    mod.controller('obraCtrl', ['$scope', '$http', 'obrasContext', '$state',
        function ($scope, $http, obrasContext, $state) {
            $http.get(obrasContext).then(function (response) {
                $scope.obrasRecords = response.data;
            });
            
           if ($state.params.obraId !== undefined) {
                $http.get(obrasContext + '/' + $state.params.obraId).then(function (response) {
                    $scope.comentariosRecords = response.data.comentarios;
                    $scope.currentObra = response.data;
                });
            } 
        }
    ]);
}
)(angular);

