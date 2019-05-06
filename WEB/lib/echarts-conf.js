    var PieChart2G = echarts.init(document.getElementById('mainPie2g'));
    var optionPie2g = {
        title: {
                text: '新疆2G流量分布图',
                //subtext: '数据来源联通告警表',
                sublink: '',
                left: 'center',
                top: 'top'
            },
        tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
     },
                 toolbox: {
                show: true,
                //orient: 'vertical',
                left: 'right',
                top: 'top',
                feature: {
                    dataView: {readOnly: false},
                    restore: {},
                    saveAsImage: {show:true}
                }
            },
    legend: {
            orient : 'vertical',
            x : 'left',
            data:(function(){
                    var res = [];
                    var len = result.length;
                        for(var i=0,size=len;i<size;i++) {
                         res.push({
                             name: result[i].city,
                         });
                        }
                        return res;
                })()

        },
        series : [
            {
                name:'2G流量',
                type:'pie',
                radius : '55%',
                center: ['65%', '50%'],
                data:(function(){
                    var res = [];
                    var len = result.length;
                        for(var i=0,size=len;i<size;i++) {
                         res.push({
                             //通过把result进行遍历循环来获取数据并放入Echarts中
                             name: result[i].city,
                             value: result[i].flow2G
                         });
                        }
                        return res;
                })()
            }
        ]
    };
    // 为echarts对象加载数据 
    PieChart2G.setOption(optionPie2g);