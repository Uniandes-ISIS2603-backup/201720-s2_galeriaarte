/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("galeriaModule");
    mod.constant("galeriaContext", "api/galerias");
    mod.controller('galeriaCtrl', ['$scope', '$http', 'galeriaContext', '$state',
        function ($scope, $http, galeriaContext, $state) {
            $http.get(galeriaContext).then(function (response) {
                $scope.galeriasRecords = response.data;
            });

            if ($state.params.galeriasId !== undefined) {
                $http.get(galeriaContext + '/' + $state.params.galeriasId).then(function (response) {
                    $scope.catalogoRecords = response.data.catalogos;
                    $scope.artistasRecords = response.data.artistas;
                    $scope.clientesRecords = response.data.artistas;
                    $scope.currentGaleria = response.data;
                }));
            }
        }
    ]);
}
)(window.angular);