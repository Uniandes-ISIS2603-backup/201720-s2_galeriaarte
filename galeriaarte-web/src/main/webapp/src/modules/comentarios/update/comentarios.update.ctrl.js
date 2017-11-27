(
        function (ng) {
            var mod = ng.module("comentarioModule");
            mod.constant("comentariosContext", "api/comentarios");
            mod.constant("clientesContext", "api/cliente");
            mod.controller('comentarioUpdateCtrl', ['$scope', '$http', 'comentariosContext', '$state', 'clientesContext', '$rootScope',
                function ($scope, $http, comentariosContext, $state, clientesContext, $rootScope) {
                    $rootScope.edit = true;

                    var idComentario = $state.params.comentarioId;

                    var idCliente = [];

                    $scope.allClientesShow = [];

                    $http.get(comentariosContext + '/' + idComentario).then(function (response) {
                        var comentario = response.data;
                        $scope.comentarioId = comentario.id;
                        $scope.comentarioContenido = comentario.contenido;
                        $scope.allClienteComentarios = comentario.cliente;
                        $scope.mergeClientes($scope.allClienteComentarios);
                    });

                    /**
                     * 
                     * @param {type} clientes
                     * @returns {undefined}
                     */
                    $scope.mergeClientes = function (clientes) {
                        for (var item in clientes) {
                            idCliente.push("" + clientes[item].id);
                        }
                        $scope.getClientes(clientes);
                    };

                    /**
                     * 
                     * @param {type} clientes
                     * @returns {undefined}
                     */
                    $scope.getClientes = function (clientes) {
                        $http.get(clientesContext).then(function (response) {
                            $scope.Allclientes = response.data;
                            $scope.clientesComentario = clientes;

                            var filteredClientes = $scope.Allclientes.filter(function (Allclientes) {
                                return $scope.clientesComentario.filter(function (clientesComentario) {
                                    return clientesComentario.id == Allclientes.id;
                                }).length == 0
                            });

                            $scope.allClientesShow = filteredClientes;

                        });
                    };

                    $scope.allowDrop = function (ev) {
                        ev.preventDefault();
                    };

                    $scope.drag = function (ev) {
                        ev.dataTransfer.setData("text", ev.target.id);
                    };

                    $scope.dropAdd = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        idCliente.push("" + data);
                    };

                    $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        var index = idCliente.indexOf(data);
                        if (index > -1) {
                            idCliente.splice(index, 1);
                        }
                    };

                    $scope.createComentario = function () {
                        $scope.newClientes();
                        $http.put(comentariosContext + "/" + idComentario, {
                            id: $scope.comentarioId,
                            contenido: $scope.comentarioContenido
                        }).then(function (response) {
                            $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                        });
                    };

                    $scope.newClientes = function () {
                        $scope.allClientesComentario = [];
                        for (var ite in idCliente) {
                            for (var all in $scope.Allclientes) {
                                if ($scope.Allclientes[all].id === parseInt(idCliente[ite])) {
                                    $scope.allClientesComentaio.push($scope.Allclientes[all]);
                                }
                            }
                        }
                    };
                }
            ]);
        }
)(window.angular);