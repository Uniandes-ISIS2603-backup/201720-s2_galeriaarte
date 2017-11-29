/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogosContext", "api/catalogos");
    mod.controller('catalogoCtrl', ['$scope', '$http', 'catalogosContext', '$state',
        function ($scope, $http, catalogosContext, $state) {
            $http.get(catalogosContext).then(function (response) {
                $scope.catalogosRecords = response.data;
            });
            
           if ($state.params.idCatalogo !== undefined) {
                $http.get(catalogosContext + '/' + $state.params.idCatalogo).then(function (response) {
            
                    $scope.currentCatalogo = response.data;
                });
            } 
        }
    ]);
}
)(angular);