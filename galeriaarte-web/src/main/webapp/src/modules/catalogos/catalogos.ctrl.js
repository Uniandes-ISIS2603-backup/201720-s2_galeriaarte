/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogos");
    mod.controller('catalogoCtrl', ['$scope', '$http', 'catalogoContext', '$state',
        function ($scope, $http, catalogoContext, $state) {
            $http.get(catalogoContext).then(function (response) {
                $scope.catalogosRecords = response.data;
            });

         //   if ($state.params.catalogosId !== undefined) {
         //       $http.get(catalogoContext + '/' + $state.params.catalogosId).then(function (response) {
         //           $scope.obrasRecords = response.data.obras;
         //           $scope.currentCatalogo = response.data;
         //       });
         //   }
        }
    ]);
}
)(window.angular);