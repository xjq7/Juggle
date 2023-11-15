const donateHtml = `
<div class="sidebar_donate">
    <div class="sidebar_sponsor">虚位以待</div>
    <div class="sidebar_sponsor_submit">
        <a href="/docs/guide/community/donate">成为赞助商</a>
    </div>
<div>
`

module.exports = {
    docs: [{
        type: 'html',
        value: donateHtml,
        defaultStyle: true
    },
    {
        type: 'category',
        label: 'Juggle是什么？',
        collapsed: true,
        items: [
            'guide/introduce/introduce-index',
            'guide/introduce/concept'
        ],
    },{
        type: 'category',
        label: '快速开始',
        collapsed: true,
        items: [
            'guide/start/quick-start',
            'guide/start/start-with-docker'
        ],
     },{
            type: 'category',
            label: '使用手册',
            collapsed: true,
            items: [
                'guide/user/domain',
                'guide/user/api',
                'guide/user/flow-definition',
                'guide/user/flow-version',
                'guide/user/data-type-info',
                'guide/user/example-api',
            ],
     },{
        type: 'category',
        label: '开源共建',
        collapsed: true,
        items: [
            'guide/open/contributing-flow'
        ],
     },{
        type: 'category',
        label: '社区',
        collapsed: true,
        items: [
            'guide/community/community-get-help',
            'guide/community/donate'
        ],
    }],
    "changelog": [{
        type: 'html',
        value: donateHtml,
        defaultStyle: true
    },
    {
        type: 'category',
        label: '1.x版本',
        link: {
            type: 'doc',
            id: "changelog/changelog-index"
        },
        items: [
            {
                type: 'autogenerated',
                dirName: 'changelog/1.x',
            },
        ]
    }
    ],
    "faq": [{
        type: 'html',
        value: donateHtml,
        defaultStyle: true
    },
    {
        type: 'category',
        label: 'FAQ',
        link: {
            type: 'doc',
            id: "faq/faq-index"
        },
        items: [
            'faq/format-exception',
        ]
    }
    ]
}