Ext.namespace("com.zz91.ads.board.ad.ad");

/**
 * ad model
 * */
Ext.define("AdFormModel",{
	extend:"Ext.data.Model",
	fields:[
		{name:"id",mapping:"id"},
		{name:"positionId",mapping:"positionId"},
		{name:"adTitle",mapping:"adTitle"},
		{name:"advertiserId",mapping:"advertiserId"},
		{name:"adDescription",mapping:"adDescription"},
		{name:"adContent",mapping:"adContent"},
		{name:"adTargetUrl",mapping:"adTargetUrl"},
		{name:"gmtStart",mapping:"gmtStart"},
		{name:"gmtPlanEnd",mapping:"gmtPlanEnd"},
		{name:"remark",mapping:"remark"},
		{name:"applicant",mapping:"applicant"},
		{name:"sequence",mapping:"sequence"},
		{name:"searchExact",mapping:"searchExact"}
	]
});

Ext.define("AdGridModel",{
	extend:"Ext.data.Model",
	fields:[
		{name:"id",mapping:"ad.id"},
		{name:"a.review_status",mapping:"ad.reviewStatus"},
		{name:"positionId",mapping:"ad.positionId"},
		{name:"adTitle",mapping:"ad.adTitle"},
		{name:"adTargetUrl",mapping:"ad.adTargetUrl"},
		{name:"adContent",mapping:"ad.adContent"},
		{name:"applicant",mapping:"ad.applicant"},
		{name:"a.online_status",mapping:"ad.onlineStatus"},
		{name:"designStatus",mapping:"ad.designStatus"},
		{name:"designer",mapping:"ad.designer"},
		{name:"advertiserId",mapping:"ad.advertiserId"},
		{name:"a.gmt_created",mapping:"ad.gmtCreated"},
		{name:"a.gmt_start",mapping:"ad.gmtStart"},
		{name:"a.gmt_plan_end",mapping:"ad.gmtPlanEnd"},
		{name:"positionName",mapping:"positionName"},
		{name:"hasExactAd",mapping:"hasExactAd"},
		{name:"advertiserName",mapping:"advertiserName"},
		{name:"requestUrl",mapping:"requestUrl"},
		{name:"a.sequence",mapping:"ad.sequence"},
		{name:"searchExact",mapping:"ad.searchExact"},
		{name:"width",mapping:"width"},
		{name:"height",mapping:"height"}
	]
});

Ext.define("PositionTreeModel",{
	extend: 'Ext.data.Model',
	fields:[{name:"id",mapping:"id"},{name:"text",mapping:"text"},{name:"leaf",mapping:"leaf"}]
});

Ext.define("com.zz91.ads.board.ad.ad.ApplyForm",{
	extend:"Ext.form.Panel",
	initComponent:function(){
		
		var c={
			bodyPadding: 5,
			fieldDefaults: {
		        labelAlign: 'right',
		        labelWidth: 60,
		        labelSeparator:""
		    },
		    autoScroll:true,
		    layout:"anchor",
		    items:[{
		    	xtype:"container",
		    	layout:"column",
		    	anchor:"100%",
		    	items:[{
		    		xtype: 'container',
					columnWidth: .5,
					layout:"anchor",
					defaults:{
						anchor:'100%',
						xtype : 'textfield'
					},
					items:[{
						name:"adTitle",
						fieldLabel:"广告名称",
						formItemCls:"x-form-item required",
						allowBlank : false
					},{
						xtype:"datefield",
						name:"gmtStartStr",
						fieldLabel:"开始时间",
						format : "Y-m-d H:i:s",
						submitFormat:"Y-m-d H:i:s",
						value:new Date(),
						allowBlank : false,
						formItemCls:"x-form-item required"
					}]
		    	},{
		    		xtype:"container",
		    		columnWidth:.5,
		    		layout:"anchor",
		    		defaults:{
		    			anchor:'100%',
						xtype : 'textfield'
		    		},
		    		items:[{
		    			name:"adTargetUrl",
						fieldLabel:"目标地址",
						formItemCls:"x-form-item required",
						allowBlank : false
		    		},{
						xtype:"datefield",
						name:"gmtPlanEndStr",
						fieldLabel:"结束时间",
						format : "Y-m-d",
						submitFormat:"Y-m-d H:i:s",
						value:new Date()
					}]
		    	}]
		    },{
				xtype:"hidden",
				name:"positionId",
				id:"positionId"
			},{
		    	xtype:"textfield",
				name:"position",
				id:"position",
				anchor:"100%",
				fieldLabel:"投放位置",
				formItemCls:"x-form-item required",
				allowBlank : false,
				listeners:{
					'focus':function(){
						var grid=Ext.create("com.zz91.ads.board.ad.advertiser.MainGrid",{
							height:250,
							region:"center"
						});
						
						grid.on("itemdblclick",function(e){
							var row = this.getSelectionModel().getLastSelected(); 
							Ext.getCmp("advertiser").setValue(row.get("name"));
							Ext.getCmp("advertiserId").setValue(row.get("id"));
							Ext.getCmp("contact").setValue(row.get("contact"));
							Ext.getCmp("phone").setValue(row.get("phone"));
							Ext.getCmp("email").setValue(row.get("email"));
							
							this.up("window").close();
						});
						
						var win= Ext.create("Ext.Window",{
							layout : 'border',
							iconCls : "add16",
							width : 600,
							autoHeight:true,
							title : "选择广告主",
							modal : true,
							items : [ grid ]
						});
				
						win.show();
					}
				}
		    },{
		    	xtype:"textfield",
		    	anchor:"100%",
		    	name:"adDescription",
		    	fieldLabel:"广告描述"
		    },{
		    	xtype:"container",
		    	layout:"column",
		    	anchor:"100%",
		    	items:[{
		    		xtype:"container",
			    	columnWidth: .5,
					layout:"anchor",
					defaults:{
						anchor:'100%',
						xtype : 'textfield'
					},
					items:[{
						xtype:"hidden",
						id:"advertiserId",
						name:"advertiserId"
					},{
						id:"advertiser",
						name:"advertiser",
						fieldLabel:"广告主",
						formItemCls:"x-form-item required",
						allowBlank : false,
						listeners:{
							'focus':function(){
								var grid=Ext.create("com.zz91.ads.board.ad.advertiser.MainGrid",{
									height:250,
									region:"center"
								});
								
								grid.on("itemdblclick",function(e){
									var row = this.getSelectionModel().getLastSelected(); 
									Ext.getCmp("advertiser").setValue(row.get("name"));
									Ext.getCmp("advertiserId").setValue(row.get("id"));
									Ext.getCmp("contact").setValue(row.get("contact"));
									Ext.getCmp("phone").setValue(row.get("phone"));
									Ext.getCmp("email").setValue(row.get("email"));
									
									this.up("window").close();
								});
								
								var win= Ext.create("Ext.Window",{
									layout : 'border',
									iconCls : "add16",
									width : 600,
									autoHeight:true,
									title : "选择广告主",
									modal : true,
									items : [ grid ]
								});
						
								win.show();
							}
						}
					},{
						name:"phone",
						id:"phone",
						readOnly:true,
						fieldLabel:"联系电话"
					}]
		    	},{
		    		xtype:"container",
			    	columnWidth: .5,
					layout:"anchor",
					defaults:{
						anchor:'100%',
						xtype : 'textfield'
					},
					items:[{
						name:"contact",
						id:"contact",
						readOnly:true,
						fieldLabel:"联系人"
					},{
						name:"email",
						id:"email",
						readOnly:true,
						fieldLabel:"邮箱"
					}]
		    	}]
		    },{
		    	xtype:"textarea",
		    	anchor:"100%",
				name:"remark",
				height:250,
				fieldLabel:"备注信息"
		    }],
		    buttons:[{
				scale:"large",
				xtype:"button",
				text:"现在申请",
				iconCls:"saveas32",
				handler:this.saveModel
			}]
		}
		
		Ext.applyIf(this,c);
		
		this.callParent();
	}
});

Ext.define("com.zz91.ads.board.ad.ad.BaseGrid",{
	extend:"Ext.grid.Panel",
	initComponent:function(){
		
		var _store=Ext.create("Ext.data.Store",{
			model:"AdGridModel",
			remoteSort:true,
			pageSize:Context.PAGE_SIZE,
			proxy:{
				type:"ajax",
				url:Context.ROOT+"/ad/ad/query.htm",
				simpleSortMode:true,
				reader: {
		            type: 'json',
		            root: 'records',
		            totalProperty:"totals"
		        }
			},
			autoLoad:false
		});
		
		var _sm=Ext.create("Ext.selection.CheckboxModel",{});
		
		var _cm=[{
				text:"编号",
				dataIndex:"id",
				width:50,
				hidden:true
			},{
				header:"状态",
				width:60,
				dataIndex:"a.review_status",
				renderer:function(value, metaData, record, rowIndex, colIndex, store){
					var cst="<img src='"+Context.ROOT+"/themes/icons/review_"+value+".png' title='"+Context.REVIEWSTATUS_CATEGORY[value]+"' />";
					var ost="<img src='"+Context.ROOT+"/themes/icons/online_"+record.get("a.online_status")+".png' title='"+Context.ONLINE_STATUS[record.get("a.online_status")]+"' />";
					//过期状态
					if(record.get("designStatus")!=null && record.get("designStatus")!=""){
						var dst="<img src='"+Context.ROOT+"/themes/icons/design_"+record.get("designStatus")+".png' title='"+Context.DESIGN_STATUS[record.get("designStatus")]+"' />";
						return cst+ost+dst;
					}
					
					return cst+ost;
				}
			},{
				header:"排序",
				width:50,
				dataIndex:"a.sequence",
				sortable:true
//				editor:{
//					xtype:"numberfield",
//					decimalPrecision:6,
//					name:"sequence",
//					id:"sequence"
//				}
			},{
				header:"广告位",
				dataIndex:"positionName",
				sortable:false,
				width:300,
				renderer:function(value, metaData, record, rowIndex, colIndex, store){
					var str=value;
					if(record.get("requestUrl")!=""){
						str="<a href='"+record.get("requestUrl")+"' target='_blank'>"+value+"</a>";
					}
					return str+"<br />规格：宽"+record.get("width")+"px;高"+record.get("height")+"px";
				}
			},{
				header:"广告",
				dataIndex:"adTitle",
				sortable:false,
				width:200,
				renderer : function(value, metadata, record, rowIndex,colIndex, store) {
					var ad= value;
					if(record.get("adTargetUrl")!="" && record.get("adTargetUrl")!=null){
						ad="<a href='"+record.get("adTargetUrl")+"' target='_blank' >"+value+"</a>";
					}
					if(record.get("adContent")!="" && record.get("adContent")!=null){
						return ad+"<br /><a href='"+record.get("adContent")+"' target='_blank' ><img src='"+Context.ROOT+"/themes/boomy/pictures16.png'></a><img src='"+record.get("adContent")+"' width='150' height='50'/>";
					}
					return ad;
				}
			},{
				header:"搜索条件",
				dataIndex:"searchExact"
			},{
				header:"申请人",
				dataIndex:"applicant",
				sortable:false
			},{
				header:"广告主",
				dataIndex:"advertiserName",
				sortable:false
			},{
				header:"申请时间",
				dataIndex:"a.gmt_created",
				sortable:true,
				renderer : function(value, metadata, record, rowIndex,colIndex, store) {
					if(value!=null){
						return Ext.util.Format.date(new Date(value.time), 'Y-m-d');
					}else{
						return "";
					}
				}
			},{
				header:"计划开始时间",
				dataIndex:"a.gmt_start",
				sortable:true,
				renderer : function(value, metadata, record, rowIndex,colIndex, store) {
					if(value!=null){
						return Ext.util.Format.date(new Date(value.time), 'Y-m-d H:i:s');
					}else{
						return "";
					}
				}
			},{
				header:"计划下线时间",
				dataIndex:"a.gmt_plan_end",
				sortable:true,
				renderer : function(value, metadata, record, rowIndex,colIndex, store) {
					if(value!=null){
						return Ext.util.Format.date(new Date(value.time), 'Y-m-d');
					}else{
						return "";
					}
				}
			}];
		
		var c={
			store:_store,
			columns:_cm,
			selModel:_sm,
			dockedItems:[{
				xtype:"pagingtoolbar",
				store:_store,
				dock:"bottom",
				displayInfo:true
			}]
		};
		
		Ext.applyIf(this,c);
		
		this.callParent();
	}
});

Ext.define("com.zz91.ads.board.ad.ad.SimpleGrid", {
	extend:"com.zz91.ads.board.ad.ad.BaseGrid",
	initComponent:function(){
		
		var c={
			tbar:["->",{
				xtype:"datefield"
			},{
				xtype:"datefield"
			},{
				xtype:"combo"
			}]
		}
		
		Ext.applyIf(this,c);
		
		this.callParent();
	}
})