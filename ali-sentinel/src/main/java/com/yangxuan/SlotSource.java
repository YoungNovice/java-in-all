package com.yangxuan;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.node.Node;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.nodeselector.NodeSelectorSlot;

/**
 * try-catch 方式（通过 SphU.entry(...)），当 catch 到BlockException时执行异常处理(或fallback)
 * if-else 方式（通过 SphO.entry(...)），当返回 false 时执行异常处理(或fallback)
 *
 * 0.1.1 版本之后 SentinelResource通过注解除了可以定义资源外，还可以指定 blockHandler 和 fallback 方法
 *
 * ResourceWrapper
 * StringResourceWrapper
 * MethodResourceWrapper
 *
 * Context是对资源操作时的上下文环境，每个资源操作(针对Resource进行的entry/exit)必须属于一个Context，
 * 如果想在调用 SphU.entry() 或 SphO.entry() 前，自定义一个context，则通过ContextUtil.enter()方法来创建。
 * context是保存在ThreadLocal中的，每次执行的时候会优先到ThreadLocal中获取，为null时会调用
 * MyContextUtil.myEnter(Constants.CONTEXT_DEFAULT_NAME, "", resourceWrapper.getType())创建一个context。
 * 当Entry执行exit方法时，如果entry的parent节点为null，表示是当前Context中最外层的Entry了，此时将ThreadLocal中的context清空。
 *
 *                    machine-root
 *                    /         \
 *                   /           \
 *           EntranceNode1   EntranceNode2
 *                 /               \
 *               /                 \
 *       DefaultNode(nodeA)   DefaultNode(nodeA)
 *               |                    |
 *               +- - - - - - - - - - +- - - - - - -> ClusterNode(nodeA);

 */
public class SlotSource {

    public static void main(String[] args) throws BlockException {
        // entrance();
        entranceSingle();
        // res();
    }

    /**
     * entry在SphU#entry方法被调用的时候创建， 即entry代表当前调用的一个标志
     * 一个context中可以调用多次entry 那么这个时候可以创建多个entry
     * entry2的parent是entry1 entry1的parent是空
     *
     * 整个节点链路的构建过程，可以发现，对于一个资源，在同个上下文中，多次调用entry，
     * 会创建多个DefaultNode节点，这些节点依次挂在上下文的入口节点EntranceNode下面，
     * 而每个节点会负责当前上下文中调用entry后一个代码块的的请求数据的统计，
     * 记住，DefaultNode是与上下文相关的，
     * 假设是不同上下文，那么会呈现之前发过的结构
     *
     * 而不是像以前那样是上下文，所以这里就已经清楚了，ClusterNode和资源绑定，即使是不同上下文，
     * 同一个资源，应该都是只有一个ClusterNode，由其进行流量统计
     *
     *
     * 核心
     * EntranceNode 和 Context.enter绑定
     * DefaultNode  和 SphU.entry
     * ClusterNode  和 资源绑定
     */
    public static void resFuZi() {
        ContextUtil.enter("contextName1");
        Entry entry1 = null;
        try {
            entry1 = SphU.entry("resourceName1");
            System.out.println("run method 1");
            Entry entry2 = null;
            try {
                entry2 = SphU.entry("resourceName2");
                System.out.println("run method 1");
            }finally {
                if (entry2 != null) {
                    entry2.exit();
                }
            }
        } catch (BlockException e) {
            e.printStackTrace();
        } finally {
            if (entry1 != null) {
                entry1.exit();
            }
        }
    }

    public static void entranceSingle() throws BlockException {
        ContextUtil.enter("entrance1", "appA");
        Entry nodeA = SphU.entry("nodeA");
        if (nodeA != null) {
            nodeA.exit();
        }
        Entry nodeB = SphU.entry("nodeB");
        if (nodeB != null) {
            nodeB.exit();
        }
        ContextUtil.exit();
    }

    /**
     * @see NodeSelectorSlot
     */
    public static void entrance() throws BlockException {
        ContextUtil.enter("entrance1", "appA");
        Entry nodeA = SphU.entry("nodeA");
        if (nodeA != null) {
            nodeA.exit();
        }
        ContextUtil.exit();

        ContextUtil.enter("entrance2", "appA");
        nodeA = SphU.entry("nodeA");
        if (nodeA != null) {
            nodeA.exit();
        }
        ContextUtil.exit();
    }

    public static void res() {
        String resourceName = "资源名称";
        Entry entry = null;
        try {
            ContextUtil.enter("asd");
            entry = SphU.entry(resourceName);
            System.out.println(entry);
        } catch (BlockException e) {
            Tracer.trace(e);
            // 获取资源失败
            // biz exception
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }

    public static void resBool() {
        Entry entry1 = null;
        if (SphO.entry("resource2")) {
            System.out.println("ok");
        } else {
            // 获取限流资源失败
        }
    }
}
